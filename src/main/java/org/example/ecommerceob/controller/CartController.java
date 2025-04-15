package org.example.ecommerceob.controller;

import org.example.ecommerceob.model.Cart;
import org.example.ecommerceob.model.Product;
import org.example.ecommerceob.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        logger.info("Received request to create a new cart.");
        Cart cart = cartService.createCart();
        logger.info("Cart created with ID: {}", cart.getId());
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            logger.warn("Received invalid request with empty or null cart ID.");
            return ResponseEntity.badRequest().build();
        }
        logger.info("Fetching cart with ID: {}", id);
        Optional<Cart> cart = cartService.getCart(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Cart with ID: {} not found.", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        logger.info("Received request to delete cart with ID: {}", id);
        cartService.deleteCart(id);
        logger.info("Cart with ID: {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Cart> addProducts(@PathVariable String id, @RequestBody List<Product> products) {
        logger.info("Received request to add products to cart with ID: {}", id);
        if (products.isEmpty()) {
            logger.warn("Received request with an empty product list for cart ID: {}", id);
            return ResponseEntity.badRequest().body(null);
        }
        try {
            Cart updatedCart = cartService.addProducts(id, products);
            logger.info("Products added to cart with ID: {}", id);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            logger.error("Error adding products to cart with ID: {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}