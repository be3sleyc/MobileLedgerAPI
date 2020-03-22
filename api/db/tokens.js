const pool = require('./');

let tokendb = {};

tokendb.lookup = (token) => {
    return pool
        .query("CALL sp_lookuptoken (?)", token)
        .then(row => {
            return row[0];
        })
        .catch(err => {
            return { db_error: err };
        });
}

tokendb.expire = () => {
    try {
        const result = pool
            .query("CALL sp_expiretokens");
    }
    catch (err) {
        return { db_error: err };
    }
}

module.exports = tokendb;