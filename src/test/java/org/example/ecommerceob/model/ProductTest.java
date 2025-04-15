package org.example.ecommerceob.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    void shouldCreateProductWithAllArgsConstructor() {
        Product product = new Product(1, "Test Product", 19.99);

        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getDescription()).isEqualTo("Test Product");
        assertThat(product.getAmount()).isEqualTo(19.99);
    }

    @Test
    void shouldSetAndGetProductFields() {
        Product product = new Product(0, null, 0);

        product.setId(2);
        product.setDescription("Another Product");
        product.setAmount(9.99);

        assertThat(product.getId()).isEqualTo(2);
        assertThat(product.getDescription()).isEqualTo("Another Product");
        assertThat(product.getAmount()).isEqualTo(9.99);
    }
}