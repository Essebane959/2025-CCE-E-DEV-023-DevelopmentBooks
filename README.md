# Development Books — Spring Boot Kata

A Java **Spring Boot** project that calculates the total price of a shopping basket of software-development books with bundle discounts.  
Built using **Test-Driven Development (TDD)** and exposed through a clean **REST API**.

---

##  About the Kata

This kata is based on *Software Development Books* by Stéphane Genicot.  
The goal is to compute the optimal price when buying different combinations of five well-known books:

1. Clean Code (Robert C. Martin, 2008)
2. The Clean Coder (Robert C. Martin, 2011)
3. Clean Architecture (Robert C. Martin, 2017)
4. Test-Driven Development by Example (Kent Beck, 2003)
5. Working Effectively with Legacy Code (Michael C. Feathers, 2004)

Each book costs **€50**, with discounts for distinct titles:

| Different Books | Discount |
|-----------------|-----------|
| 2 | 5 % |
| 3 | 10 % |
| 4 | 20 % |
| 5 | 25 % |

---

## Tech Stack

- **Java 25**
- **Spring Boot 3.5.7**
- **JUnit 5**
- **Mockito**
- **Springdoc OpenAPI** (Swagger UI)

---

## Pre-requirements

Before running the app, make sure you have:

| Tool | Version | Purpose |
|------|----------|----------|
| Java JDK 25+| required | compile & run the project |
| Maven 3.9+ | required | build & manage dependencies |
| Postman (optional) | optional | test API endpoints |

---

##  Running the App

### 1️⃣ Build & run
```bash
mvn spring-boot:run

```
## 📫 Test via Postman & Swagger


### How to use swagger
Swagger is automatically available when you run the application.
1. Start your app:
2. Open your browser and go to:
   👉 http://localhost:8080/swagger-ui.html
3. You can explore and test all endpoints interactively using the "Try it out" button.
### How to use Postman
A ready-to-use Postman collection is included in this repository:  
📁 **`calculatedBooks.postman_collection.json`**

This file contains preconfigured requests to test the API easily.


1. Open **Postman**.
2. Click **File → Import**.
3. Select the file:
4. The collection includes:
- `GET /api/books` → retrieves all available books.
- `POST /api/checkout` → calculates the total basket price.
5. Make sure your Spring Boot app is running at `http://localhost:8080`.
6. Click **Send** to execute the requests.

