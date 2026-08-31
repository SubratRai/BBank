# BBank

Production-style banking backend built with Spring Boot, JWT authentication, role-based access control, and MySQL.

## Why This Project Matters

BBank is a backend-focused banking system that demonstrates how I design secure business applications with real workflows instead of only basic CRUD. It covers authentication, account operations, transaction handling, beneficiary management, and admin controls in a structured Spring Boot codebase.

This is the kind of backend architecture I can build for fintech MVPs, internal finance tools, and business platforms that need security, auditability, and clean separation of concerns.

## Core Features

- User registration and login
- JWT-based authentication and protected APIs
- Role-based access for customer and admin flows
- Account creation and account details lookup
- Deposit and withdrawal operations
- Fund transfer between accounts
- Beneficiary creation and management
- Transaction history retrieval
- Admin account freeze control
- Centralized exception handling and validation

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Maven
- Docker

## Architecture

The project follows a layered backend structure:

- `controller` for API endpoints
- `service` and `service.impl` for business logic
- `repository` for persistence access
- `dto` for request and response contracts
- `entity` for domain models
- `security` for authentication and JWT filtering
- `exception` for error handling

This structure keeps the code maintainable and makes it easier to extend with new modules.

## Business Workflows Implemented

### Customer Flow

- Register and log in
- Create an account
- Deposit and withdraw funds
- Transfer money
- View account and transaction details
- Add beneficiaries

### Admin Flow

- View users
- View accounts
- Freeze accounts when needed

## API Overview

### Authentication

- `POST /auth/register`
- `POST /auth/login`

### Accounts

- `POST /accounts`
- `GET /accounts`
- `GET /accounts/{accountNumber}`
- `POST /accounts/{accountNumber}/deposit`
- `POST /accounts/{accountNumber}/withdraw`
- `POST /accounts/{accountNumber}/transfer`
- `GET /accounts/{accountNumber}/transactions?page=0&size=10`

### Beneficiaries

- `POST /beneficiaries`
- `GET /beneficiaries`

### Admin

- `GET /admin/users`
- `GET /admin/accounts`
- `PATCH /admin/accounts/{accountNumber}/freeze`

## Local Setup

### Prerequisites

- Java 17+
- Maven
- MySQL

### Run the Project

```bash
git clone https://github.com/SubratRai/BBank.git
cd BBank
./mvnw spring-boot:run
```

Update `src/main/resources/application.properties` with your local database configuration if required.

## Screenshots

This repository already includes screenshots. If you want better client conversion, place the best 2 to 4 UI or API screenshots directly under this section with short captions.

## What This Demonstrates To Clients

- Secure backend development
- Spring Boot project organization
- Authentication and authorization design
- Financial workflow implementation
- API-first application development

## Freelance Relevance

If you need a backend for a banking workflow, wallet system, customer portal, or admin-controlled business application, this project is a good example of the kind of system I can build and extend.
