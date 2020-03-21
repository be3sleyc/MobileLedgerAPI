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

userdb.one_e = (email) => {
    return pool
        .query("CALL sp_getuseremail (?)", [email])
        .then(row => {
            return row[0];
        })
        .catch(err => {
            return { error: err };
        });
};

userdb.one_i = (id) => {
    return pool
        .query("CALL sp_getuserid (?)", [id])
        .then(row => {
            return row[0];
        })
        .catch(err => {
            return { error: err };
        });
};

// let attr = [first_name, last_name, email, pwd];
userdb.new = (attr) => {
    return pool
        .query("CALL sp_adduser (?, ?, ?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { error: err };
        })
}

// let attr = [email, pwd];
userdb.login = (attr) => {
    return pool
        .query("CALL sp_login (?, ?)", attr)
        .then(res => {
            return res;
        })
        .catch(err => {
            return { error: err };
        })
}

module.exports = userdb;