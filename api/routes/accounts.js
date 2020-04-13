const express = require('express');
const router = express.Router();

const accountdb = require('../db/accounts');
const validate = require('../validation');
const auth = require('./verifyToken');

// return all accounts of logged in user
router.get('/', auth, (req, res) => {
    try {
        let result = accountdb.all(req.user.id);
        result.then(rows => {
            res.status(200).json({ message: "Fetch successful", accounts: rows });
        });
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/:id', auth, (req, res) => {
    try {
        let id = parseInt(req.params.id);

        const { error } = validate.ids({ id: id });
        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let result = accountdb.one([req.user.id, id]);
        result.then(row => {
            res.status(200).json({ message: "Fetch successful", account: row[0] })
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/:id/edit', auth, (req, res) => {
    try {
        let name = "";
        let type = "";
        let notes = "";

        if (req.body.name) { name = req.body.name; }
        if (req.body.type !== undefined) { type = req.body.type; }
        if (req.body.notes !== undefined) { notes = req.body.notes; }

        const { error } = validate.editaccount({ id: req.params.id, name: name, type: type, notes: notes });
        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let result = accountdb.edit([req.params.id, req.user.id, name, type, notes]);
        result.then(row => {
            if (row.affectedRows == 1) {
                res.status(200).json({ message: "Edit successful" });
            } else {
                res.status(400).json({ message: "Edit failed" });
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.post('/add', auth, (req, res) => {
    try {
        let balance = null;
        let notes = '';

        if (req.body.balance !== undefined) { balance = req.body.balance.replace('$', ''); }
        if (req.body.notes !== undefined) { notes = req.body.notes; }

        const { error } = validate.newaccount({ name: req.body.name, type: req.body.type, balance: balance, notes: notes });
        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let result = accountdb.add([req.user.id, req.body.name, req.body.type, balance, notes]);
        result.then(row => {
            if (row.affectedRows == 1) {
                res.status(201).json({ message: 'New account successfully created' });
            } else {
                res.status(400).json({ message: 'Failed creating new account' });
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

module.exports = router;