const pool = require('./');

let transactiondb = {};

transactiondb.all = (uid) => {
    return pool
        .query("CALL sp_gettransactions (?)", [uid])
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
};

// let attr = [uid, aid, amount, date, payee, description, category];
transactiondb.add = (attr) => {
    return pool
        .query("CALL sp_addtransaction (?, ?, ?, ?, ?, ?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [id, uid, aid, date, payee, description, amount, category];
transactiondb.edit = (attr) => {
    return pool
        .query("CALL sp_edittransaction (?, ?, ?, ?, ?, ?, ?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, id];
transactiondb.delete = (attr) => {
    return pool
        .query("CALL sp_deletetransaction (?, ?)", attr)
        .then(row => {
            return row;
        })
        .catch(err => {
            return { db_error: err };
        });
};

module.exports = transactiondb;