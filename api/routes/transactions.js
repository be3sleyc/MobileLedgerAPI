const express = require('express');
const router = express.Router();

const transactiondb = require('../db/transactions');
const auth = require('./verifyToken');
const validate = require('../validation');

router.get('/', auth, (req, res) => {
    try {
        let result = transactiondb.all(req.user.id);
        result.then(rows => {
            res.status(200).json({ message: 'Fetch successful', transactions: rows[0] })
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/:id/edit', auth, (req, res) => {
    try {
        let id = parseInt(req.params.id);

        const ierror = validate.ids({ id: id }).error;

        if (ierror) {
            return res.status(400).json({ message: ierror.details[0].message });
        }

        const { error } = validate.transactionedit(req.body);

        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let accountid = (req.body.accountid === undefined ? null : req.body.accountid);
        let amount = (req.body.amount === undefined ? null : req.body.amount);
        let paiddate = (req.body.paiddate === undefined ? null : req.body.paiddate);
        let payee = (req.body.payee === undefined ? null : req.body.payee);
        let description = (req.body.description === undefined ? null : req.body.description);
        let category = (req.body.category === undefined ? null : req.body.category);

        let result = transactiondb.edit([id, req.user.id, accountid, paiddate, payee, description, amount, category]);
        result.then(success => {
            if (success.affectedRows == 1) {
                res.status(200).json({ message: 'Edit successful' })
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.post('/log', auth, (req, res) => {
    try {

        const { error } = validate.newtransaction(req.body);

        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let description = (req.body.description === undefined ? null : req.body.description)

        let result = transactiondb.add([req.user.id, req.body.accountid, req.body.amount, req.body.paiddate, req.body.payee, description, req.body.category]);
        result.then(success => {
            if (success.affectedRows == 1) {
                res.status(201).json({ message: 'Creation successful' })
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/:id/delete', auth, (req, res) => {
    try {
        let id = parseInt(req.params.id);

        const { error } = validate.ids({ id: id });
        if (error) {
            return res.status(400).json({ message: error.details[0].message });
        }

        let result = transactiondb.delete([req.user.id, id]);
        result.then(success => {
            if (success.affectedRows == 1) {
                res.status(200).json({ message: 'Delete successful' })
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

module.exports = router;
