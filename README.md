# 📊 BudgetBuddy App

BudgetBuddy is a Spring Boot–based expense tracking application with **JWT-based authentication**.  
It allows users to sign up, log in, and manage personal expenses securely.  
API documentation is available via Swagger UI.

---

## 🚀 Features
- **User Authentication** (JWT)
- **Sign Up / Login**
- **Add Expense**
- **View Expenses**
- **Delete Expense**
- **Interactive API Docs** via Swagger UI

---

## 🛠 Tech Stack
- **Backend:** Spring Boot, Spring Security, JWT
- **Build Tool:** Maven
- **Database:** (Configure in `application.properties`)
- **API Documentation:** SpringDoc OpenAPI / Swagger UI

---

## 📜 API Endpoints

### 🔐 Authentication

#### **Sign Up**
`POST /signup`  
Registers a new user.  

**Request Body:**
```json
{
  "username": "ironman",
  "email": "ironman@gmail.com",
  "password": "ironman_pass"
}
```

**Response:**
```json
{
  "message": "Sign up successful"
}
```

---

#### **Login**
`POST /login`  
Authenticates user and returns a JWT token.  

**Request Body:**
```json
{
  "username": "misha",
  "password": "misha"
}
```

**Response:**
```json
{
  "redirect": "/dashboard",
  "token": "<jwt-token>"
}
```

---

### 💰 Expenses
> All expense endpoints require **Authorization: Bearer <JWT Token>**

#### **Add Expense**
`POST /send-personal-expense`  

**Request Body:**
```json
{
  "expenseDescription": "shake",
  "expenseAmount": 100,
  "expenseDate": "2025-08-11"
}
```

**Response:**
```json
{
  "message": "expense saved"
}
```

---

#### **View Expenses**
`GET /get-personal-expenses`  

**Response:**
```json
{
  "expenses": [
    {
      "id": 2,
      "description": "shake",
      "amount": 100,
      "expenseDate": "2025-08-11",
      "createdAt": "2025-08-12T02:29:04.615671"
    }
  ]
}
```

---

#### **Delete Expense**
`POST /delete-expense`  

**Request Body:**
```json
{
  "expenseId": 2
}
```

**Response:**
```json
{
  "message": "expense delete successfully"
}
```

---

## 📄 API Documentation
Once the application is running, access the interactive Swagger UI at:  
```
http://localhost:8080/swagger-ui/index.html
```

Raw OpenAPI spec:  
```
http://localhost:8080/v3/api-docs
```

---

## 🔑 Authorization in Swagger
1. Open Swagger UI.
2. Click **Authorize** in the top right corner.
3. Enter your JWT token as:
```
Bearer <your_token_here>

# For testing purpose you can also use as following:
# The app will accept this and skip JWT Authentication

Bearer Test <username> 
```
4. Click **Authorize** to send the token with all secured requests.

---

## ⚡ Getting Started

### Prerequisites
- Java 17+
- Maven 3+
- A running database (configure `application.properties`)

### Run Locally
```bash
# Clone the repository
git clone https://github.com/<your-username>/budgetbuddy.git
cd budgetbuddy

# Build and run the project
mvn spring-boot:run
```

---

## 📌 License
This project is licensed under the MIT License.
