package org.example.ecommerceob.repository;

import org.example.ecommerceob.model.Cart;
import org.example.ecommerceob.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CartRepositoryTest {

    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        cartRepository = new CartRepository();
    }

    @Test
    void shouldCreateCart() {
        Cart cart = cartRepository.createCart();

        assertThat(cart).isNotNull();
        assertThat(cart.getId()).isNotNull();
        assertThat(cart.getProducts()).isEmpty();

        Optional<Cart> retrieved = cartRepository.getCart(cart.getId());
        assertThat(retrieved).isPresent();
    }

    @Test
    void shouldRetrieveCartAndUpdateLastAccessed() throws InterruptedException {
        Cart cart = cartRepository.createCart();
        String id = cart.getId();

        LocalDateTime oldAccess = cart.getLastAccessed();
        Thread.sleep(10);
        Optional<Cart> retrieved = cartRepository.getCart(id);

        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getLastAccessed()).isAfter(oldAccess);
    }

    @Test
    void shouldUpdateCart() {
        Cart cart = cartRepository.createCart();
        cart.getProducts().add(new Product(1, "item", 5.0));

        cartRepository.updateCart(cart);
        Optional<Cart> updated = cartRepository.getCart(cart.getId());

        assertThat(updated).isPresent();
        assertThat(updated.get().getProducts()).hasSize(1);
    }

    @Test
    void shouldDeleteCart() {
        Cart cart = cartRepository.createCart();
        String id = cart.getId();

        cartRepository.deleteCart(id);

        Optional<Cart> deleted = cartRepository.getCart(id);
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldReturnAllCarts() {
        cartRepository.createCart();
        cartRepository.createCart();

        assertThat(cartRepository.getAllCarts()).hasSize(2);
    }
}
