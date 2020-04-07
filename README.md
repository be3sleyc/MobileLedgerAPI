# Capstone

Capstone project for Information Technology: Network Management and Security Bachelor's Degree at Utah Valley University - Spring 2020

API and mobile app for tracking personal cash flows.

## API Calls and expected successful response:

* POST /api/users/register {first name, last name, email, password} 200 OK
* PUT /api/users/login {email, password} 200 token, \[user]
* GET /api/users/logout {token} 200 OK


* GET /api/accounts/ {token} 200 \[accounts]
* GET /api/accounts/:id {token} 200 \[account]
* PUT /api/accounts/:id/edit {token, name, type, notes} 200 OK
* POST /api/accounts/add {token, name, type, balance, notes} 200 OK 


* GET /api/transactions/ {token} 200 \[transactions]
* GET /api/transactions/:id {token} 200 \[transactions]
* GET /api/transactions/range/:start/:stop {token} 200 \[transactions]
* GET /api/transactions/categories/:category {token} 200 \[transactions]
* GET /api/transactions/categories/:category/range/:start/:stop {token} 200 \[transactions]
* GET /api/transactions/payees/:payee {token} 200 \[transactions]
* GET /api/transactions/payees/:payee/range/:start/:stop {token} 200 \[transactions]
* GET /api/transactions/accounts/:account {token} 200 \[transactions]
* GET /api/transactions/accounts/:account/range/:start/:stop {token} 200 \[transactions]
* GET /api/transactions/categorylist {token} 200 \[categories]
* PUT /api/transactions/:id/edit/ {token, accountid, amount, paiddate, payee, description, category} 200 OK
* POST /api/transactions/log {token, accountid, amount, paiddate, payee, description, category} 200 OK

