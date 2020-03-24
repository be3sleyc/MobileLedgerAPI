const express = require('express');
const router = express.Router();

const transactiondb = require('../db/transactions');
const auth = require('./verifyToken');
const validate = require('../validation');

router.get('/categorylist', auth, (req, res) => {
    try {
        let result = transactiondb.categories(req.user.id);
        result.then(rows => {
            res.json(rows[0]);
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/', auth, (req, res) => {
    try {
        let result = transactiondb.all(req.user.id);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/:id', auth, (req, res) => {
    try {
        let id = parseInt(req.params.id);

        const { error } = validate.ids({ id: id });
        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.one([req.user.id, id]);
        result.then(row => {
            res.json(row[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});
router.get('/range/:start/:stop', auth, (req, res) => {
    try {
        const { error } = validate.dates(req.params);

        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.some([req.user.id, req.params.start, req.params.stop]);
        result.then(rows => {
            res.json(rows);
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/categories/:category', auth, (req, res) => {
    try {
        const { error } = validate.category(req.params);

        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.getcatall([req.user.id, req.params.category]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/categories/:category/range/:start/:stop', auth, (req, res) => {
    try {
        const cerror = validate.category(req.params).error;

        if (cerror) {
            return res.status(400).json({ validation_error: cerror.details[0].message });
        }

        const derror = validate.dates(req.params).error;

        if (derror) {
            return res.status(400).json({ validation_error: derror.details[0].message });
        }

        let result = transactiondb.getcatsome([req.user.id, req.params.category, req.params.start, req.params.stop]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/payees/:payee', auth, (req, res) => {
    try {
        const { error } = validate.payee(req.params);

        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.getpayeeall([req.user.id, req.params.payee]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/payees/:payee/range/:start/:stop', auth, (req, res) => {
    try {
        const perror = validate.payee(req.params).error;

        if (perror) {
            return res.status(400).json({ validation_error: perror.details[0].message });
        }

        const derror = validate.dates(req.params).error;

        if (derror) {
            return res.status(400).json({ validation_error: derror.details[0].message });
        }

        let result = transactiondb.getpayeesome([req.user.id, req.params.payee, req.params.start, req.params.stop]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/accounts/:account', auth, (req, res) => {
    try {
        const { error } = validate.account(req.params);

        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.getaccountall([req.user.id, req.params.account]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.get('/accounts/:account/range/:start/:stop', auth, (req, res) => {
    try {
        const aerror = validate.account(req.params).error;

        if (aerror) {
            return res.status(400).json({ validation_error: aerror.details[0].message });
        }

        const derror = validate.dates(req.params).error;

        if (derror) {
            return res.status(400).json({ validation_error: derror.details[0].message });
        }

        let result = transactiondb.getaccountsome([req.user.id, req.params.account, req.params.start, req.params.stop]);
        result.then(rows => {
            res.json(rows[0])
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.put('/:id/edit', auth, (req, res) => {
    try {
        let id = parseInt(req.params.id);

        const ierror = validate.ids({ id : id }).error;

        if (ierror) {
            return res.status(400).json({ validation_error: ierror.details[0].message });
        }

        const { error } = validate.transactionedit(req.body);

        if (error) {
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let accountid = (req.body.accountid === undefined ? null : req.body.accountid);
        let amount = (req.body.amount === undefined ? null : req.body.amount);
        let paiddate = (req.body.paiddate === undefined ? null : req.body.paiddate);
        let payee = (req.body.payee === undefined ? null : req.body.payee);
        let category = (req.body.category === undefined ? null : req.body.category);

        let result = transactiondb.edit([req.user.id, id, accountid, amount, paiddate, payee, category]);
        result.then(success => {
            if(success.affectedRows == 1) {
                res.sendStatus(200);
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
            return res.status(400).json({ validation_error: error.details[0].message });
        }

        let result = transactiondb.add([req.user.id, req.body.accountid, req.body.amount, req.body.paiddate, req.body.payee, req.body.category]);
        result.then(success => {
            if(success.affectedRows == 1) {
                res.sendStatus(200);
            }
        })
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

module.exports = router;