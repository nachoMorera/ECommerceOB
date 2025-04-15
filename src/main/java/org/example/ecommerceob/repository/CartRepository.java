package org.example.ecommerceob.repository;

import org.example.ecommerceob.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CartRepository {
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    public Cart createCart() {
        String id = UUID.randomUUID().toString();
        Cart cart = new Cart(id, new ArrayList<>(), LocalDateTime.now());
        carts.put(id, cart);
        logger.info("Created cart with ID: {}", id);
        return cart;
    }

    public Optional<Cart> getCart(String id) {
        Cart cart = carts.get(id);
        if (cart != null) {
            cart.setLastAccessed(LocalDateTime.now());
            logger.info("Accessed cart with ID: {}", id);
        } else {
            logger.warn("Cart not found with ID: {}", id);
        }
        return Optional.ofNullable(cart);
    }

    public void deleteCart(String id) {
        if (carts.remove(id) != null) {
            logger.info("Deleted cart with ID: {}", id);
        } else {
            logger.warn("Attempted to delete non-existent cart with ID: {}", id);
        }
    }

    public void updateCart(Cart cart) {
        if (cart == null || cart.getId() == null) {
            throw new IllegalArgumentException("Cart or cart ID must not be null");
        }
        cart.setLastAccessed(LocalDateTime.now());
        carts.put(cart.getId(), cart);
        logger.info("Updated cart with ID: {}", cart.getId());
    }

    public Collection<Cart> getAllCarts() {
        return carts.values();
    }

    public void removeInactiveCarts(long maxInactiveMinutes) {
        LocalDateTime now = LocalDateTime.now();
        carts.entrySet().removeIf(entry ->
                Duration.between(entry.getValue().getLastAccessed(), now).toMinutes() >= maxInactiveMinutes
        );
        logger.info("Inactive carts older than {} minutes were removed", maxInactiveMinutes);
    }
}
