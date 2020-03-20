const express = require('express');
const router = express.Router();
const userdb = require('../db/users');
const accountdb = require('../db/accounts');
const transactiondb = require('../db/transactions');

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
router.get('/:id', async (req, res, next) => {
    try {
        let result = userdb.one(req.params.id);
        result.then(row => { res.json(row) });
    } catch (e) {
        console.log("route error:", e);
        res.sendStatus(500);
    }
});

// open to anyone except logged in users.
router.post('/register', (req, res, next) => {
    try {
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
                let params = [req.body.firstname, req.body.lastname, req.body.email, req.body.password];
                let result = userdb.new(params);
                result.then(success => { res.json(success) });
            }
        } else {
            res.json({ error: "You can't access this page logged in" })
        }
    } catch (e) {
        res.status(500).send("route error:", e);
    }

});

router.post('/login', (req, res, next) => {
    res.json({});
});

module.exports = router;