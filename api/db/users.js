const pool = require('./');

let userdb = {};

userdb.all = () => {
    return pool
        .query("SELECT * FROM `Users`")
        .then(rows => {
            return rows;
        })
        .catch(err => {
            return { error: err };
        });
};

userdb.one = (id) => {
    return pool
        .query("SELECT * FROM `Users` WHERE id = ?", [id])
        .then(row => {
            return row[0];
        })
        .catch(err => {
            return { error: err };
        });
};

userdb.new = (attr) => {
    let [first_name, last_name, email, pwd] = attr;
    return pool
        .query("INSERT INTO `Users` (givenname, surname, email, password) values (?, ?, ?, ?)", [first_name, last_name, email, pwd])
        .then(res => {
            console.log(res);
        })
        .catch(err => {
            return { error: err };
        })
}

module.exports = userdb;