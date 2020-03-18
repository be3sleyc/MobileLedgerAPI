const pool = require('./');

let userdb = {};

userdb.all = () => {
    pool
        .query(`SELECT * FROM Users`)
        .then(rows => {
            return rows[0];
        })
        .catch(err => {
            return { error: err };
        });
    pool.end()
        .then(() => { })
        .catch(err => {
            console.log(err);
        });
};

userdb.one = (id) => {
    pool
        .query(`SELECT * FROM Users WHERE id = ?`, [id])
        .then(rows => {
            return rows[0];
        })
        .catch(err => {
            return { error: err };
        });
    pool.end()
        .then(() => { })
        .catch(err => {
            console.log(err);
        });
};

module.exports = userdb;