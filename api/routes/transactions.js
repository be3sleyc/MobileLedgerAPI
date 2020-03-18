const express = require('express');
const router = express.Router();

router.get('/', (req, res, next) => {
    res.json({ transaction: 'test' });
});

router.post('/log', (req, res, next) => {
    res.json({ transaction: 'new' });
});

module.exports = router;