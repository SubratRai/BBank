# BBank - Banking Application Backend

## Tech Stack
- Java 17
- Spring Boot 3
- Maven
- MySQL
- Spring Security + JWT
- Spring Data JPA
- Bean Validation

## Project Structure
```
src/main/java/com/subrat/bbank
├── BBankApplication.java
├── config
│   └── SecurityConfig.java
├── controller
│   ├── AccountController.java
│   ├── AdminController.java
│   ├── AuthController.java
│   └── BeneficiaryController.java
├── dto
├── entity
├── enums
├── exception
│   ├── BadRequestException.java
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── UnauthorizedException.java
├── repository
├── security
│   ├── CustomUserDetailsService.java
│   └── JwtAuthenticationFilter.java
├── service
│   ├── AccountService.java
│   ├── AdminService.java
│   ├── AuthService.java
│   ├── BeneficiaryService.java
│   └── impl
└── util
    └── JwtUtil.java
```

## Setup
1. Create MySQL database (or let app auto-create): `bbank_db`.
2. Update `src/main/resources/application.properties` if needed.
3. Run application:
```bash
./mvnw spring-boot:run
```

## API Endpoints
### Auth
- `POST /auth/register`
- `POST /auth/login`

### Customer
- `POST /accounts`
- `GET /accounts`
- `GET /accounts/{accountNumber}`
- `POST /accounts/{accountNumber}/deposit`
- `POST /accounts/{accountNumber}/withdraw`
- `POST /accounts/{accountNumber}/transfer`
- `GET /accounts/{accountNumber}/transactions?page=0&size=10`
- `POST /beneficiaries`
- `GET /beneficiaries`

### Admin
- `GET /admin/users`
- `GET /admin/accounts`
- `PATCH /admin/accounts/{accountNumber}/freeze`

## Sample Postman Payloads
### Register (Customer)
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "Password@123",
  "role": "CUSTOMER"
}
```

### Register (Admin)
```json
{
  "name": "System Admin",
  "email": "admin@example.com",
  "password": "Admin@12345",
  "role": "ADMIN"
}
```

### Login
```json
{
  "email": "john@example.com",
  "password": "Password@123"
}
```

### Create Account
```json
{
  "accountType": "SAVINGS"
}
```

### Deposit/Withdraw
```json
{
  "amount": 1500.00
}
```

### Transfer
```json
{
  "destinationAccountNumber": "1234567890",
  "amount": 200.00
}
```

### Add Beneficiary
```json
{
  "name": "Alice",
  "accountNumber": "9988776655",
  "bankName": "XYZ Bank"
}
```

## Database Schema
- SQL schema file: `src/main/resources/schema.sql`
- Auto DDL is enabled with `spring.jpa.hibernate.ddl-auto=update`
