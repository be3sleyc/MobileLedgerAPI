const pool = require('./');

let accountdb = {};

accountdb.all = () => {
    return new Promise((resolve, reject) => {
        pool.query(`SELECT * FROM Accounts`, (err, result) => {
            if(err) {
                return reject(err);
            }
            return resolve(results);
        });
    });
};

accountdb.one = (id) => {
    return new Promise((resolve, reject) => {
        pool.query(`SELECT * FROM Accounts id = ?`, [id], (err, result) => {
            if(err) {
                return reject(err);
            }
            return resolve(results[0]);
        });
    });
};

module.exports = accountdb;