const mariadb = require('mariadb');

const pool = mariadb.createPool({
    password: process.env.DB_PASSWORD,
    user: process.env.DB_USER,
    host: process.env.DB_HOST,
    port: process.env.DB_PORT,
    database: process.env.DB_DATABASE
});

module.exports = pool;