package org.example.ecommerceob.service;

import org.example.ecommerceob.exception.CartNotFoundException;
import org.example.ecommerceob.exception.InvalidProductException;
import org.example.ecommerceob.model.Cart;
import org.example.ecommerceob.model.Product;
import org.example.ecommerceob.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart() {
        logger.info("Creating a new cart.");
        Cart cart = cartRepository.createCart();
        logger.info("Cart created with ID: {}", cart.getId());
        return cart;
    }

    public Optional<Cart> getCart(String id) {
        logger.info("Fetching cart with ID: {}", id);
        Optional<Cart> cart = cartRepository.getCart(id);
        if (cart.isPresent()) {
            logger.info("Cart found with ID: {}", id);
        } else {
            logger.warn("Cart not found with ID: {}", id);
        }
        return cart;
    }

    public void deleteCart(String id) {
        logger.info("Deleting cart with ID: {}", id);
        cartRepository.deleteCart(id);
        logger.info("Cart with ID: {} deleted successfully.", id);
    }

    public void addProduct(String cartId, Product product) {
        if (product.getAmount() < 0) {
            logger.error("Invalid product amount: {}", product.getAmount());
            throw new InvalidProductException("Product amount cannot be negative.");
        }

        logger.info("Adding product to cart with ID: {}", cartId);
        Cart cart = cartRepository.getCart(cartId)
                .orElseThrow(() -> {
                    logger.error("Cart not found with ID: {}", cartId);
                    return new CartNotFoundException(cartId);
                });

        cart.getProducts().add(product);
        cartRepository.updateCart(cart);
        logger.info("Product added to cart with ID: {}", cartId);
    }

    public Cart addProducts(String cartId, List<Product> products) {
        logger.info("Adding products to cart with ID: {}", cartId);

        for (Product product : products) {
            if (product.getAmount() < 0) {
                logger.error("Invalid product amount: {}", product.getAmount());
                throw new InvalidProductException("Product amount cannot be negative: " + product.getDescription());
            }
        }

        Cart cart = cartRepository.getCart(cartId)
                .orElseThrow(() -> {
                    logger.error("Cart not found with ID: {}", cartId);
                    return new CartNotFoundException(cartId);
                });

        cart.getProducts().addAll(products);
        cartRepository.updateCart(cart);
        logger.info("Products added to cart with ID: {}", cartId);
        return cart;
    }
}