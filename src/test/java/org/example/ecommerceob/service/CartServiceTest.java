package org.example.ecommerceob.service;

import org.example.ecommerceob.model.Cart;
import org.example.ecommerceob.model.Product;
import org.example.ecommerceob.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart("1", new ArrayList<>(), LocalDateTime.now());
        product = new Product(1, "Product 1", 100.0);
    }

    @Test
    void testCreateCart() {
        when(cartRepository.createCart()).thenReturn(cart);

        Cart createdCart = cartService.createCart();
        assertNotNull(createdCart);
        assertEquals("1", createdCart.getId());
    }

    @Test
    void testGetCart() {
        when(cartRepository.getCart("1")).thenReturn(Optional.of(cart));

        Optional<Cart> retrievedCart = cartService.getCart("1");
        assertTrue(retrievedCart.isPresent());
        assertEquals("1", retrievedCart.get().getId());
    }

    @Test
    void testAddProduct() {
        when(cartRepository.getCart("1")).thenReturn(Optional.of(cart));
        cartService.addProduct("1", product);

        assertEquals(1, cart.getProducts().size());
        assertEquals("Product 1", cart.getProducts().get(0).getDescription());
    }

    @Test
    void testDeleteCart() {
        cartService.deleteCart("1");
        verify(cartRepository, times(1)).deleteCart("1");
    }

    @Test
    void testAddProducts() {
        when(cartRepository.getCart("1")).thenReturn(Optional.of(cart));
        cartService.addProducts("1", List.of(product));

        assertEquals(1, cart.getProducts().size());
    }
}