# 🛒 Simple E-Commerce Cart Backend (Spring Boot)

This is a small backend app I built using **Java**, **Spring Boot 3.4.4**, and **Maven**. It simulates a basic cart system like the one you'd see in an e-commerce site.

I did the whole thing in about **3 hours and a half**. The goal was to complete all requirements with clean, readable code.

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
