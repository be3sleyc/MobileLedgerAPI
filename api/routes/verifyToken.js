const jwt = require('jsonwebtoken');

const tokendb = require('../db/tokens');

module.exports = (req, res, next) => {
    const token = req.header('auth-token');

    // if there is no token, deny
    if (!token) {
        return res.status(401).json({ auth_error: 'Access Denied' });
    }

    try {
        let blacklist = tokendb.lookup(token);
        blacklist.then( row => {
            console.log("blacklist:", row[0]);
        });
    } catch(e) {
        res.status(500).json({ route_error: e });
    }

    jwt.verify(token, process.env.JWT_SECRET, (err, payload) => {
        if (err) {
            console.log(err)
            return res.status(401).json({ error: 'Invalid Token' });
        } 

        req.user = payload;
        next();
    });
}