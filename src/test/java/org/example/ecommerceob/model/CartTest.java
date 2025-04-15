package org.example.ecommerceob.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    @Test
    void shouldCreateCartWithAllArgsConstructor() {
        List<Product> products = Arrays.asList(new Product(1, "Test", 10));
        LocalDateTime now = LocalDateTime.now();
        Cart cart = new Cart("123", products, now);

        assertThat(cart.getId()).isEqualTo("123");
        assertThat(cart.getProducts()).containsExactlyElementsOf(products);
        assertThat(cart.getLastAccessed()).isEqualTo(now);
    }

    @Test
    void shouldSetAndGetCartFields() {
        Cart cart = new Cart();
        cart.setId("1234");
        cart.setProducts(List.of(new Product(2, "Test 2", 20.0)));
        LocalDateTime time = LocalDateTime.now();
        cart.setLastAccessed(time);

        assertThat(cart.getId()).isEqualTo("1234");
        assertThat(cart.getProducts()).hasSize(1);
        assertThat(cart.getLastAccessed()).isEqualTo(time);
    }
}