<div align="center">

# 🌻 Flipzon – E-Commerce Platform 🛍️

### *a cozy little corner of the internet for buying & selling things* ☕

<img src="https://img.shields.io/badge/Java-17-e8b4a2?style=flat-square&logo=openjdk&logoColor=white" />
<img src="https://img.shields.io/badge/Spring%20Boot-Microservices-b5c99a?style=flat-square&logo=springboot&logoColor=white" />
<img src="https://img.shields.io/badge/Angular-Frontend-d6a4a4?style=flat-square&logo=angular&logoColor=white" />
<img src="https://img.shields.io/badge/MySQL-Database-e0c097?style=flat-square&logo=mysql&logoColor=white" />
<img src="https://img.shields.io/badge/Maven-Build-c9ada7?style=flat-square&logo=apachemaven&logoColor=white" />
<img src="https://img.shields.io/badge/Eureka-Service%20Discovery-f2d0a9?style=flat-square&logo=spring&logoColor=white" />

</div>

---

## 🍂 Overview

Flipzon is a full-stack e-commerce web application built on a **microservices architecture**. It lets customers browse products, manage their cart, and place orders — while retailers and admins get their own cozy little dashboards to manage products and users.

Business logic is split into independent Spring Boot microservices for scalability, modularity, and maintainability, all tied together with service discovery and an API Gateway. 🌿

---

## 🧡 Features

### 🛒 Customer
- User Registration & Login
- Browse Products
- Search Products
- Product Filtering
- Product Sorting
- Product Details
- Shopping Cart
- Place Orders
- View Order History
- User Profile Management

### 🏪 Retailer
- Add Products
- Update Products
- Delete Products
- Manage Inventory
- View Product Information

### 🛠️ Admin
- Manage Retailers
- Verify Product Updates
- Manage Users
- Monitor Products
- Administrative Dashboard

---

## 🍯 Microservices

The backend is divided into independent, self-contained services:

- User Service
- Product Service
- Cart Service
- Order Service
- Retailer Service
- Admin Service
- API Gateway
- Eureka Discovery Server
- Common Library

Each service manages its own business logic and talks to the others via REST APIs. 🌾

---

## 🍄 Technology Stack

**Frontend**
- Angular · TypeScript · HTML5 · CSS3 · Bootstrap

**Backend**
- Java 17 · Spring Boot · Spring MVC · Spring Data JPA · Spring Security · Hibernate

**Microservices**
- Spring Cloud · Eureka Discovery Server · Spring Cloud Gateway · REST APIs

**Database**
- MySQL

**Build Tool**
- Maven

**Dev Tools**
- Eclipse IDE · Visual Studio Code · Postman · Git · GitHub

---

## 🕯️ Project Architecture

```
Angular Frontend
        │
        ▼
   API Gateway
        │
 ┌──────┼───────────────┐
 │      │      │        │
 ▼      ▼      ▼        ▼
User  Product Cart   Order
Svc    Svc    Svc     Svc
        │
        ▼
 Retailer Service
        │
        ▼
  Admin Service
        │
        ▼
  MySQL Database

  Service Discovery
   (Eureka Server)
```

---

## 🌼 Repository Structure

```
flipzon-workspace/
│
├── flipzon-admin-service/
├── flipzon-api-gateway/
├── flipzon-cart-service/
├── flipzon-common/
├── flipzon-discovery-server/
├── flipzon-order-service/
├── flipzon-product-service/
├── flipzon-retailer-service/
├── flipzon-user-service/
│
└── frontend/
```

Each backend service is structured like this:

```
src/
 ├── controller/
 ├── service/
 ├── service/impl/
 ├── repository/
 ├── model/
 ├── dto/
 ├── response/
 ├── exception/
 ├── config/
 ├── util/
 └── resources/
      └── application.properties
```

---

## 🍞 Database

- MySQL relational database
- Separate tables for Users, Products, Orders, Cart, Retailers, and Admin operations
- JPA/Hibernate for ORM
- CRUD operations via Spring Data JPA

---

## 🌸 API Communication

- RESTful APIs
- JSON Request & Response
- API Gateway for centralized routing
- Service registration via Eureka Discovery Server

---

## 🔐 Security

- User Authentication
- Role-Based Access (Admin, Retailer, Customer)
- Input Validation
- Exception Handling
- Secure Database Operations

---

## ✨ Key Functionalities

- Product Management
- Shopping Cart
- Order Management
- User Management
- Retailer Dashboard
- Admin Dashboard
- Product Search, Filtering & Sorting
- Inventory Management
- Service Discovery
- API Gateway Routing

---

## 🪴 Project Highlights

- Full-stack application
- Microservices architecture
- REST API integration
- Modular backend design
- Responsive frontend
- Database-driven application
- Scalable, clean, layered architecture
- Independent service deployment
- Centralized API Gateway
- Service discovery with Eureka

---

## 🧵 Backend Layered Architecture

```
Controller
      │
      ▼
Service Interface
      │
      ▼
Service Implementation
      │
      ▼
Repository
      │
      ▼
Database
```

**Supporting Layers:** DTO · Entity (Model) · Configuration · Exception Handling · Utility Classes · Response Wrappers

---

## 🌟 Prerequisites

- Java 17+
- Maven
- Node.js
- Angular CLI
- MySQL Server
- Eclipse IDE / IntelliJ IDEA
- Visual Studio Code
- Git

---

## 🍂 How to Run

### Backend
1. Clone the repository.
2. Configure the MySQL database.
3. Update the `application.properties` files.
4. Start the Eureka Discovery Server.
5. Start the API Gateway.
6. Start all microservices.
7. Verify service registration in Eureka.

### Frontend
1. Navigate to the frontend directory.
2. Install dependencies using `npm install`.
3. Run the application using `ng serve`.
4. Open the application in the browser.

---

<div align="center">

### made with 🍵, 🐈, and a little too much coffee

<img src="https://media.giphy.com/media/JIX9t2j0ZTN9S/giphy.gif" width="280" alt="cute cat" />

**Author:** thejaswini-peddiraju 🌷

</div>
