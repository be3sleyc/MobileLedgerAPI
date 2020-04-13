const fs = require('fs');
const https = require('https');
const http = require('http')
const express = require('express');
const bodyParser = require('body-parser');


require('dotenv').config();

const apiRouter = require('./routes')

const options = {
    key: fs.readFileSync(process.env.KEY_PATH),
    cert: fs.readFileSync(process.env.CERT_PATH)
}

const app = express();

app.use(bodyParser.json());
app.use('/api/', apiRouter);

app.use(function (req, res, next) {
    res.status(404);

    res.send({ message: '404 Not found' });
    return;
});

const sport = 3000
const port = 3030

http.createServer(app).listen(port, () => {
    console.log(`Server live on port: ${port} - http`);
});

https.createServer(options, app).listen(sport, () => {
    console.log(`Server live on port: ${sport} - https`);
});