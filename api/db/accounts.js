const pool = require('./');

let accountdb = {};

accountdb.all = (uid) => {
    return pool
        .query("CALL sp_getAccounts (?)", [uid])
        .then(rows => {
            return rows[0];
        })
        .catch(err => {
            return { db_error: err };
        });
};

// let attr = [uid, name, type, balance, note];
accountdb.add = (attr) => {
    return pool
        .query("CALL sp_addaccount (?, ?, ?, ?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { db_error: err };
        })
}

// let attr = [id, uid, name, type, note];
accountdb.edit = (attr) => {
    return pool
        .query("CALL sp_editaccount (?, ?, ?, ?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { db_error: err };
        });
}

accountdb.close = (attr) => {
    return pool
        .query("CALL sp_closeaccount (?, ?)", attr)
        .then(res => { 
            return res; 
        })
        .catch(err => { 
            return { db_error: err }; 
        })
}

module.exports = accountdb;