const pool = require('./');

let transactiondb = {};

// let attr = [uid, id];
transactiondb.one = (attr) => {
    return pool
        .query("CALL sp_gettransaction (?, ?)", attr)
        .then(row => {
            return row;
        })
        .catch(err => {
            return { db_error: err };
        });
};

// let attr = [uid, start, stop];
transactiondb.some = (attr) => {
    return pool
        .query("CALL sp_getrange (?, ?, ?)", attr)
        .then(rows => {
            return rows
        })
        .catch(err => {
            return { db_error: err };
        });
};

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

// let attr = [uid, category];
transactiondb.getcatall = (attr) => {
    return pool
        .query("CALL sp_getcattransactions (?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, category, start, stop];
transactiondb.getcatsome= (attr) => {
    return pool
        .query("CALL sp_getcattransactionrange (?, ?, ?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, payee];
transactiondb.getpayeeall = (attr) => {
    return pool
        .query("CALL sp_getpaytransactions (?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, payee, start, stop];
transactiondb.getpayeesome = (attr) => {
    return pool
        .query("CALL sp_getpaytransactionrange (?, ?, ?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, account];
transactiondb.getaccountall = (attr) => {
    return pool
        .query("CALL sp_getaccounttransactions (?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

// let attr = [uid, account, start, stop];
transactiondb.getaccountsome = (attr) => {
    return pool
        .query("CALL sp_getaccounttransactionrange (?, ?, ?, ?)", attr)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        });
}

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

// let attr = [uid, id, aid, amount, date, payee, description, category];
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

transactiondb.categories = (uid) => {
    return pool
        .query("CALL sp_getcategories (?)", uid)
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { db_error: err };
        })
}

module.exports = transactiondb;