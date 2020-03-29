const fs = require('fs');
const https = require('https');
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
https.createServer(options, app).listen(process.env.PORT || '3000', () => {
    console.log(`Server live on port: ${process.env.PORT || '3000'}`);
});