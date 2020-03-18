const express = require('express');
const router = express.Router();

const userRouter = require('./users');
const accountRouter = require('./accounts');
const transactionRouter = require('./transactions');

router.use('/users/', userRouter);
router.use('/accounts/', accountRouter);
router.use('/transactions', transactionRouter);

router.get('/', (req, res, next) => {
    res.json({ test: 'test' });
});

module.exports = router;