const express = require('express');
const bcrypt = require('bcrypt');
const router = express.Router();
const userdb = require('../db/users');
const accountdb = require('../db/accounts');
const transactiondb = require('../db/transactions');

const saltrounds = 10;

// should be disabled
router.get('/', (req, res, next) => {
    try {
        let results = userdb.all();
        results.then(rows => { res.json(rows) });
    } catch (e) {
        console.log(e);
        res.sendStatus(500);
    }
});

// should be disabled
router.get('/:email', async (req, res, next) => {
    try {
        let result = userdb.one_e(req.params.email);
        result.then(row => { res.json(row) });
    } catch (e) {
        console.log("route error:", e);
        res.sendStatus(500);
    }
});

// open to anyone except logged in users.
router.post('/register', (req, res) => {
    try {
        // client shouldn't be logged in
        if (true) {
            let missing_err = '{ "missing_value_errors":[ {';

            if (req.body.firstname == null) {
                missing_err += '"firstname_error" : "Missing value for firstname!"';
            }
            if (req.body.lastname == null) {
                missing_err += (missing_err.length > 28 ? ", " : "");
                missing_err += '"lastname_error" : "Missing value for lastname!"';
            }
            if (req.body.email == null) {
                missing_err += (missing_err.length > 28 ? ", " : "");
                missing_err += '"email_error" : "Missing value for email!"';
            }
            if (req.body.password == null) {
                missing_err += (missing_err.length > 28 ? ", " : "");
                missing_err += '"password_error" : "Missing value for password!"';
            }

            if (missing_err.length > 28) {
                missing_err += "} ] }"
                res.json(JSON.parse(missing_err));
            } else {
                // check if email has already been used
                let email = req.body.email;
                let find = userdb.one_e(email);
                find.then(row => {
                    if (!(row.length > 0)) {
                        bcrypt.hash(req.body.password, saltrounds, (err, hash) => {
                            if (err) {
                                res.json({ error: err });
                            } else {
                                let params = [req.body.firstname, req.body.lastname, email, hash];
                                let result = userdb.new(params);
                                result.then(success => {
                                    res.json(success[0])
                                });
                            }
                        });
                    } else {
                        res.json({ error: 'email already in use' });
                    }
                });
            }
        } else {
            res.status(403).json({ error: "You can't access this page logged in" })
        }
    } catch (e) {
        res.status(500).send("route error:", e);
    }
});

router.post('/login', (req, res) => {
    // get email and username form request
    try {
        // client shouldn't be logged in
        if (true) {
            let missing_err = '{ "missing_value_errors":[ {';

            if (req.body.email == null) {
                missing_err += (missing_err.length > 28 ? ", " : "");
                missing_err += '"email_error" : "Missing value for email!"';
            }
            if (req.body.password == null) {
                missing_err += (missing_err.length > 28 ? ", " : "");
                missing_err += '"password_error" : "Missing value for password!"';
            }
            if (missing_err.length > 28) {
                missing_err += "} ] }"
                res.json(JSON.parse(missing_err));
            } else {
                // check db for user with email and get hash
                let email = req.body.email;
                let find = userdb.one_e(email);
                find.then(user => {
                    if (user.length === 1) {
                        let pwd = user[0].password.toString();

                        // compare password and hash
                        bcrypt.compare(req.body.password, pwd, (err, result) => {
                            if (err) {
                                res.json({ login_error: 'bad password' });
                            } else if (result) {
                                // if match, login with hash
                                let result = userdb.login([email, pwd]);
                                result.then(user => { 
                                    res.json(user[0]) 
                                });
                            } else {
                                res.json({ login_error: 'bad email or password' });
                            }
                        });
                    } else {
                        res.json({ login_error: 'bad email or password' });
                    }
                });
            }
        } else {
            res.status(403).json({ error: "You can't access this page logged in" })
        }
    } catch (e) {
        res.status(500).json({ 'route error': e });
    }
});

module.exports = router;