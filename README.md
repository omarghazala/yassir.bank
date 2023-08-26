<h2>My Posts Application</h2>

- Yassir Bank is a backend Java API to a fake financial institution using Java and Spring

<h2>Technologies</h2>

- JDK 17

<h2>Installation</h2>

- Clone project and run it.
- Check http://localhost:8080/ for API
- Check http://localhost:8080/h2-console for H2 database

<h2>User Guide</h2>

Create some dummy users :

1) Use the API : http://localhost:8080/customers/createAll
2) User in the body of the API :

[
    {
        "id": 1,
        "name": "Arisha Barron"
    },
    {
        "id": 2,
        "name": "Branden Gibson"
    },
    {
        "id": 3,
        "name": "Rhonda Church"
    },
    {
        "id": 4,
        "name": "Georgina Hazel"
    }
]

Create a new bank account for a customer, with an initial deposit amount. A
single customer may have multiple bank accounts :

1) Use the API : http://localhost:8080/bankAccounts/create/{customerId}
2) Replace {customerId} by one the dummy users' Id
3) In the body use :

{
    "initialDeposit": 20500
}

Transfer amounts between any two accounts, including those owned by
different customers :

1) Use the API : http://localhost:8080/transactions/transfer
2) In the body use 2 customer ids and a transfer amount :

{
    "amount": 100,
    "sourceBankAccountId": 3,
    "targetBankAccountId": 1
}

Retrieve balances for a given account :

1) Use the API : http://localhost:8080/bankAccounts/get/{bankAccountId}
2) Replace {bankAccountId} with an ID of any account

Retrieve transfer history for a given account :

1) Use the API : http://localhost:8080/transactions/get/{bankAccountId}
2) Replace {bankAccountId} with an ID of any account







