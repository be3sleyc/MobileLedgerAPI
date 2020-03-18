const pool = require('./');

let transactiondb = {};

transactiondb.all = () => {
    return new Promise((resolve, reject) => {
        pool.query(`SELECT * FROM Transactions`, (err, result) => {
            if(err) {
                return reject(err);
            }
            return resolve(results);
        });
    });
};

transactiondb.one = (id) => {
    return new Promise((resolve, reject) => {
        pool.query(`SELECT * FROM Transactions WHERE id = ?`, [id], (err, result) => {
            if(err) {
                return reject(err);
            }
            return resolve(results[0]);
        });
    });
};

module.exports = transactiondb;