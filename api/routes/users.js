const express = require('express');
const router = express.Router();
const userdb = require('../db/users');
const accountdb = require('../db/accounts');
const transactiondb = require('../db/transactions');

router.get('/', async (req, res, next) => {
    try {
        let results = await userdb.all();
        res.json(results);
    } catch(e) {
        console.log(e);
        res.sendStatus(500);
    }
});

router.get('/:id', async (req, res, next) => {
    try {
        console.log(req.params.id);
        let results = await userdb.one(req.params.id);
        res.json(results);
    } catch(e) {
        console.log(e);
        res.sendStatus(500);
    }
});

router.post('/register', (req, res, next) => {
    res.json({ user: 'new' });
});

router.post('/login', (req, res, next) => {
    res.json({});
});

module.exports = router;