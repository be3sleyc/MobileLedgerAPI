# Capstone

Capstone project for Information Technology: Network Management and Security Bachelor's Degree at Utah Valley University - Spring 2020

API and DB info for tracking personal cash flows.

## API Calls and expected successful response:

* POST /api/users/register {first name, last name, email, password} 201 OK
* PUT /api/users/login {email, password} 200 token, \[user]
* PUT /api/users/logout {token} 200 OK


* GET /api/accounts/ {token} 200 \[accounts]
* PUT /api/accounts/:id/edit {token, name, type, notes} 200 OK
* POST /api/accounts/add {token, name, type, balance, notes} 201 OK 
* PUT /api/accounts/:id/close {token) 200 OK


* GET /api/transactions/ {token} 200 \[transactions]
* GET /api/transactions/categorylist {token} 200 \[categories]
* PUT /api/transactions/:id/edit/ {token, accountid, amount, paiddate, payee, description, category} 200 OK
* POST /api/transactions/log {token, accountid, amount, paiddate, payee, description, category} 201 OK
* PUT /api/transactions/:id/delete {token} 200 OK

