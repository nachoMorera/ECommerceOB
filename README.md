# 🛒 Simple E-Commerce Cart Backend (Spring Boot)

This is a small backend app I built using **Java**, **Spring Boot 3.4.4**, and **Maven**. It simulates a basic cart system like the one you'd see in an e-commerce site.

---

## ✅ Features

- Create a shopping cart with a random UUID
- Add products to the cart
- View the contents of the cart
- Delete a cart
- Carts are deleted automatically after 10 minutes of inactivity
- Everything is stored in memory (no DB)

---

## 💻 Tech Stack

- Java 17+
- Spring Boot 3.4.4
- Maven
- Lombok
- Spring Web
- Spring Boot DevTools

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/example/ecommerceob/
│   │       ├── controller/
│   │       │   └── CartController.java
│   │       ├── exception/
│   │       │   ├── CartNotFoundException.java
│   │       │   └── InvalidProductException.java
│   │       ├── model/
│   │       │   ├── Cart.java
│   │       │   └── Product.java
│   │       ├── repository/
│   │       │   └── CartRepository.java
│   │       ├── scheduler/
│   │       │   └── CartCleanupScheduler.java
│   │       ├── service/
│   │       │   └── CartService.java
│   │       └── ECommerceObApplication.java
│   └── resources/
│
└── test/
    └── java/
        └── org/example/ecommerceob/
            ├── controller/
            │   └── CartControllerTest.java
            ├── model/
            │   ├── CartTest.java
            │   └── ProductTest.java
            ├── repository/
            │   └── CartRepositoryTest.java
            ├── service/
            │   └── CartServiceTest.java
            └── ECommerceObApplicationTests.java
```

---

## 🚀 How to Run

Make sure you have Java 17+ and Maven installed.

Then just run:

```bash
mvn spring-boot:run
```

---

## 🧪 How to Test the Project

You can use Postman or curl. Here are some examples:

### 1. Create a Cart

**POST** `/cart`

```bash
curl -X POST http://localhost:8080/cart
```

Returns a cart ID like:

```json
"ea2c81de-3456-4453-9f65-2fc827f227cd"
```

---

### 2. Add Products to the Cart

**POST** `/cart/{id}/products`

Replace `{id}` with the cart ID from the previous step.

**Example JSON to send:**

```json
[
  {
    "name": "Mouse",
    "price": 25.50
  },
  {
    "name": "Monitor",
    "price": 120.00
  }
]
```

**Using curl:**

```bash
curl -X POST http://localhost:8080/cart/{id}/products \
     -H "Content-Type: application/json" \
     -d '[{"name":"Mouse","price":25.50},{"name":"Monitor","price":120.00}]'
```

---

### 3. Get Cart by ID

**GET** `/cart/{id}`

```bash
curl http://localhost:8080/cart/{id}
```

---

### 4. Delete a Cart

**DELETE** `/cart/{id}`

```bash
curl -X DELETE http://localhost:8080/cart/{id}
```
