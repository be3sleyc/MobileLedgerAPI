const express = require('express');
const router = express.Router();

router.get('/', (req, res, next) => {
    try {
        res.json({ transaction: 'test' });
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

router.post('/log', (req, res, next) => {
    try {
        res.json({ transaction: 'new' });
    } catch (e) {
        res.status(500).json({ route_error: e });
    }
});

module.exports = router;