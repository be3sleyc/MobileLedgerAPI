const express = require('express');
const bodyParser = require('body-parser');


require('dotenv').config();

const apiRouter = require('./routes')

const app = express();
app.use(bodyParser.json());
app.use('/api/', apiRouter);
app.listen(process.env.PORT || '3000', () => {
    console.log(`Server live on port: ${process.env.PORT || '3000'}`);
});