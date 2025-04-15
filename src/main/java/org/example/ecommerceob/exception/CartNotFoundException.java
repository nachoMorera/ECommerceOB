package org.example.ecommerceob.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Cart with ID " + id + " not found.");
    }
}
