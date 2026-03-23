# Retailer Rewards Program

## Overview
This project implements a **Retailer Rewards Program** using Java and SpringBoot.  
Customers earn reward points based on their purchase transactions. The application exposes REST APIs to calculate and retrieve rewards, with clean error handling and demo data preloaded via SQL scripts.

##  Project Structure
The project follows a standard layered architecture:

- **model** → JPA entities  
  - `Customer` → Represents a customer with transactions.  
  - `Transaction` → Represents a purchase transaction linked to a customer.  

- **repository** → Persistence layer  
  - `CustomerRepository` → CRUD operations for customers.  
  - `TransactionRepository` → CRUD operations for transactions.  

- **service** → Business logic  
  - `RewardsService` → Contains reward calculation rules.  

- **controller** → REST endpoints  
  - `RewardsController` → Exposes APIs to fetch rewards for customers.  

- **exception** → Error handling  
  - `CustomerNotFoundException` → Thrown when a customer ID does not exist.  
  - `GlobalExceptionHandler` → Handles exceptions globally and returns clean JSON responses.  

- **resources**  
  - `data.sql` → SQL script to preload demo customers and transactions into the H2 database.  

- **test** → Unit and integration tests  
  - `RewardsServiceTest` → Validates reward calculation logic.  
  - `RewardsControllerIntegrationTest` → Validates API responses with seeded data.

---

## Reward Calculation Rules
- **1 point** for every dollar spent over **$50**.  
- **2 points** for every dollar spent over **$100**.  

**Example:**  
- Transaction of $120 →  
  - 50 points (for $50–$100 range)  
  - 40 points (for $20 over $100, at 2 points per dollar)  
  - **Total = 90 points**

---

**Access endpoints:**
        1.GET /api/rewards/{customerId} → Rewards for a specific customer
        2.GET /api/rewards → Rewards for all customers
**Database:**
     Used H2 in-memory database by default.
     Demo data is loaded automatically from data.sql.
     Access H2 console at: "http://localhost:8080/h2-console"

**Testing**
- Unit Tests:
      RewardsServiceTest ensures reward calculation rules are applied correctly.
- Integration Tests:
      RewardsControllerIntegrationTest validates API responses with seeded data.
      Tests cover positive scenarios (valid customers) and negative scenarios (non-existent customers).


** Demo Data**
    The application uses data.sql to preload sample customers and transactions:
    - Customers: Alice (ID=1), Bob (ID=2)
    - Transactions:
    - Alice → $120 (Jan), $75 (Feb)
    - Bob → $200 (Jan)




   
