# webApp
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
# Reward calculation logic
if (amount <= 50)
    points = 0;
else if (amount <= 100)
    points = amount - 50;
else
    points = 50 + (amount - 100) * 2;

# API Endpoints
Method         Endpoint                                         Description
Get            /api/rewards/calculate?startDate=2025-10-01&     Return monthly and total reward points
               endDate=2025-12-31

http://localhost:8765/api/rewards/calculate?startDate=2025-10-01&endDate=2025-12-31

Get           http://localhost:8765/api/rewards/transactions     Return all transactions

Post          http://localhost:8765/api/rewards/transaction        Add new transactions for existing customers

# Sample json 

{
    "customerId":"C001",
    "amount":90.50,
    "date":"2025-09-27"
}

{
    "customerId":"C002",
    "amount":100.50,
    "date":"2025-08-09"
}
{
    "customerId":"C003",
    "amount":120,
    "date":"2025-06-17"
}
