### 🌍 Project 2

Simple CRUD REST API for managing **Products** built with **Spring Boot 3.5**, **Spring Web**, **Spring Data JPA**, **H2 in-memory DB**, and **springdoc-openapi (Swagger UI)**.

---
## Tech stack
- Java 21, Maven
- Spring Boot 3.5 (Web, Data JPA)
- H2 (in-memory) database
- springdoc-openapi (Swagger UI)
- DTO mapping + layered architecture
- Centralized exception handling

---
## How it works (high-level)
- **Domain**: `Product` is a JPA entity persisted in H2 (`@Entity(name = "products")`).
- **Repository**: `ProductRepository` extends `JpaRepository<Product, Long>` → all CRUD methods are available out of the box.
- **Service**: `ProductService` contains business logic and maps between DTOs and entity.
- **Controller**: `ProductController` exposes REST endpoints under `/api/v1/products`.
- **DTOs**:
    - `ProductRequest` – create request (name)
    - `UpdateProductRequest` – update request (name, id — id is not strictly needed because it’s in the path)
    - `ProductResponse` – response (id, name)
- **Mapping**: `ProductMapper` converts between DTOs and entity.
- **Errors**:
    - `ProductNotFoundException` thrown when product not found
    - `ProductExceptionAdvisor` maps it to HTTP **404** with body `{ "message": "..." }`

> There is also an old in-memory repository `OldProductRepository` kept only for reference. It is **not** annotated with `@Repository`, so it isn’t used by Spring.

---

## Run locally

### Prerequisites
- Java 21
- Maven 3.9+

### Start the app
```bash
mvn spring-boot:run
# or
mvn clean package && java -jar target/second-project-java-spring-0.0.1-SNAPSHOT.jar

```
App starts on http://localhost:8080

---
## Swagger / OpenAPI

- Swagger UI: http://localhost:8080/swagger-ui/index.html

- OpenAPI JSON: http://localhost:8080/v3/api-docs

---
## H2 console (optional)

- http://localhost:8080/console/

- JDBC URL: jdbc:h2:mem:testdb

- Username: sa (blank password)

Configured in src/main/resources/application.properties.

---
## API

Base path: /api/v1/products 

### 1) Create product
- POST http://localhost:8080/api/v1/products
- Body: 
```bash
{ "name": "First product" }
```
- Response: 201 Created
```bash
{ "id": 1, "name": "First product" }
```

### 2) Get product by id
- GET http://localhost:8080/api/v1/products/1
- Response 200 OK
```bash
{ "id": 1, "name": "First product" }
```
- If not found 404 Not Found
```bash
{ "message": "Product with 123 not found" }
```

### 3) Create product
- POST http://localhost:8080/api/v1/products
- Body:
```bash
{ "name": "Second product" }
```
- Response: 201 Created
```bash
{ "id": 2, "name": "Second product" }
```

### 4) Update product
- PUT http://localhost:8080/api/v1/products/2
- Body:
```bash
{ "name": "Modified second product" }
```
- Response 200 OK
```bash
{ "id": 2, "name": "Modified second product" }
```

### 5) List all products
- GET http://localhost:8080/api/v1/products
- Response: 200 OK
```bash
[
  { "id": 1, "name": "First product" },
  { "id": 2, "name": "Modified second product" }
]
```

### 6) Delete product
- DELETE http://localhost:8080/api/v1/products/2
- Response: 204 No Content
- If not found: 404 Not Found
---
### Project structure (key parts)

src/main/java/pl/edu/vistula/second_project_java_spring
├─ product
│   ├─ api
│   │   ├─ ProductController.java
│   │   ├─ request
│   │   │   ├─ ProductRequest.java
│   │   │   └─ UpdateProductRequest.java
│   │   └─ response
│   │       └─ ProductResponse.java
│   ├─ domain
│   │   └─ Product.java
│   ├─ repository
│   │   ├─ ProductRepository.java      (JPA)
│   │   └─ OldProductRepository.java   (legacy, not used)
│   ├─ service
│   │   └─ ProductService.java
│   └─ support
│       ├─ ProductMapper.java
│       ├─ ProductExceptionAdvisor.java
│       ├─ ProductExceptionSupplier.java
│       └─ exception
│           └─ ProductNotFoundException.java
├─ shared
│   └─ api
│       └─ response
│           └─ ErrorMessageResponse.java
└─ SecondProjectJavaSpringApplication.java

---
### 🧑‍💻 Author
Marko Kobyliukh Vistula University — Computer Engineering