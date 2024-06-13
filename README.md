# Banking System Demo

This project is a demo banking system implemented to showcase various API security issues and their quick fixes. The demo includes endpoints to demonstrate vulnerabilities and solutions for common API security problems.

## Features

- Demonstrates API security issues and their fixes:
  - Broken Object Level Authorization
  - Broken Authentication
  - Unrestricted Resource Consumption
  - Broken Function Level Authorization
  - Server Side Request Forgery (SSRF)
  - Security Misconfiguration
- Provides both vulnerable and secure endpoints for educational purposes.

## Database

The banking system demo uses a MySQL database to store information about users, accounts, and transactions. The database schema is designed to support the core functionalities of the system, ensuring data integrity and security.

### Tables

1. **Users**: Stores user information, including credentials and roles.
   - `id` (INT, Primary Key): Unique identifier for each user.
   - `username` (VARCHAR): Username of the user.
   - `password` (VARCHAR): Hashed password of the user.
   - `role` (VARCHAR): Role of the user (e.g., USER, ADMIN).

2. **Accounts**: Stores account information linked to users.
   - `id` (INT, Primary Key): Unique identifier for each account.
   - `owner_id` (INT, Foreign Key): References the user who owns the account.
   - `balance` (DECIMAL): The current balance of the account.

3. **Transfers**: Records transaction details between accounts.
   - `id` (INT, Primary Key): Unique identifier for each transfer.
   - `from_account_id` (INT, Foreign Key): References the account from which the amount is debited.
   - `to_account_id` (INT, Foreign Key): References the account to which the amount is credited.
   - `amount` (DECIMAL): The amount of money transferred.



| Endpoint                         | Method | Description                                      |
|----------------------------------|--------|--------------------------------------------------|
| `/users/login/vulnerable`        | POST   | Vulnerable login endpoint                        |
| `/users/login/secure`            | POST   | Secure login endpoint                            |
| `/users/create/vulnerable`       | POST   | Vulnerable user creation endpoint                |
| `/users/create/secure`           | POST   | Secure user creation endpoint                    |


| Endpoint                           | Method | Description                                      |
|------------------------------------|--------|--------------------------------------------------|
| `/accounts/vulnerable/{accountId}` | GET    | Vulnerable endpoint to get account details       |
| `/accounts/secure/{accountId}`     | GET    | Secure endpoint to get account details           |
| `/accounts/create`                 | POST   | Endpoint to create a new account                 |
| `/accounts/update/{accountId}`      | PUT    | Endpoint to update account details               |
| `/accounts/delete/{accountId}`      | DELETE | Endpoint to delete an account                    |

| Endpoint                | Method | Description                                      |
|-------------------------|--------|--------------------------------------------------|
| `/transfers/vulnerable` | POST   | Vulnerable endpoint to transfer funds            |
| `/transfers/secure`     | POST   | Secure endpoint to transfer funds                |


| Endpoint                      | Method | Description                                      |
|-------------------------------|--------|--------------------------------------------------|
| `/resources/fetch/vulnerable` | POST   | Vulnerable endpoint to fetch resources           |
| `/resources/fetch/secure`     | POST   | Secure endpoint to fetch resources               |


| Endpoint                    | Method | Description                                      |
|-----------------------------|--------|--------------------------------------------------|
| `/files/upload/vulnerable`  | POST   | Vulnerable endpoint to upload files              |
| `/files/upload/secure`      | POST   | Secure endpoint to upload files                  |


