const express = require('express');
const router = express.Router();

router.get('/', (req, res, next) => {
    res.json({ account: 'test' });
});

router.post('/add', (req, res, next) => {
    res.json({ account: 'new' });
});

module.exports = router;