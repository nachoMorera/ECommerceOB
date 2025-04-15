package org.example.ecommerceob.controller;

import org.example.ecommerceob.model.Cart;
import org.example.ecommerceob.model.Product;
import org.example.ecommerceob.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1, "Product 1", 100.0);
        cart = new Cart("1", List.of(product), LocalDateTime.now());
    }

    @Test
    void testCreateCart() throws Exception {
        when(cartService.createCart()).thenReturn(cart);

        mockMvc.perform(post("/api/carts"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testGetCart() throws Exception {
        when(cartService.getCart("1")).thenReturn(Optional.of(cart));

        mockMvc.perform(get("/api/carts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testGetCartNotFound() throws Exception {
        when(cartService.getCart("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/carts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCart() throws Exception {
        doNothing().when(cartService).deleteCart("1");

        mockMvc.perform(delete("/api/carts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testAddProducts() throws Exception {
        when(cartService.addProducts("1", List.of(product))).thenReturn(cart);

        mockMvc.perform(post("/api/carts/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"description\":\"Product 1\",\"amount\":100}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].description").value("Product 1"));
    }
}