const express = require('express');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

const router = express.Router();

const userdb = require('../db/users');
const validate = require('../validation');
const auth = require('./verifyToken');

const saltrounds = 10;

router.get('/', auth, (req, res) => {
    try {
        let result = userdb.one_i(req.user.id);
        result.then(rows => {
            let user = rows[0]
            res.status(200).json({ message: 'Fetch successful', user });
        });
    } catch (e) {

        res.status(500).json({ route_error: e });
    }
});

// open to anyone except logged in users.
router.post('/register', (req, res) => {
    try {
        // client shouldn't be logged in
        const token = req.header('auth-token');
        if (!token) {
            const { error } = validate.register(req.body);
            if (error) {
                return res.status(400).json({ message: error.details[0].message });
            }

            // check if email has already been used
            let email = req.body.email.toLowerCase();
            let find = userdb.one_e(email);
            find.then(row => {
                if (row.length < 1) {
                    bcrypt.hash(req.body.password, saltrounds, (err, hash) => {
                        if (err) {
                            res.status(500).json({ error: err });
                        } else {
                            let params = [req.body.firstname, req.body.lastname, email, hash];
                            let result = userdb.new(params);
                            result.then(success => {
                                if (success.affectedRows == 1) {
                                    res.status(201).json({ message: 'Registration successful' });
                                }
                            });
                        }
                    });
                } else {
                    res.status(403).json({ message: 'email already in use' });
                }
            });
        } else {
            res.status(403).json({ message: "You can't access this page with a token" })
        }
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/login', (req, res) => {
    // get email and username from request
    try {
        // client shouldn't be logged in
        const token = req.header('auth-token');
        if (!token) {
            const { error } = validate.login(req.body);

            if (error) {
                return res.status(400).json({ message: error.details[0].message })
            }
            // check db for user with email and get hash
            let email = req.body.email.toLowerCase();
            let find = userdb.one_e(email);
            find.then(user => {
                if (user.length === 1) {
                    let pwd = user[0].password.toString();

                    // compare password and hash
                    bcrypt.compare(req.body.password, pwd, (err, result) => {
                        if (err) {
                            res.status(403).json({ message: "bad password" });
                        } else if (result) {
                            // if match, login with hash
                            let result = userdb.login([email, pwd]);
                            result.then(user => {
                                // should return a JWT
                                const token = jwt.sign(user[0][0], process.env.JWT_SECRET, { expiresIn: "14d" }, (err, token) => {
                                    if (err) {
                                        return res.status(500).json({ token_error: err });
                                    }
                                    let repUser = user[0][0]
                                    repUser['token'] = token
                                    res.header('auth-token', token).status(200).json({ message: "Login Successful", user: repUser });
                                });

                            });
                        } else {
                            res.status(400).json({ message: "bad email or password" });
                        }
                    });
                } else {
                    res.status(400).json({ message: "bad email or password" });
                }
            });
        } else {
            res.status(403).json({ message: "You can't access this page with a token" })
        }
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/logout', auth, (req, res) => {
    try {
        const token = req.header('auth-token');
        let expdate = new Date(0);
        expdate.setUTCSeconds(req.user.exp);
        let result = userdb.logout([token, req.user.id, expdate]);
        result.then(row => {
            if (row.affectedRows == 1) {
                res.status(200).json({ message: 'Logout OK' })
            } else {
                res.status(406).json({ message: 'Logout failed' });
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

module.exports = router;
