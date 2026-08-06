package com.coforge.training.flipzon.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.flipzon.cartservice.dto.CartRequest;
import com.coforge.training.flipzon.cartservice.dto.CartResponse;
import com.coforge.training.flipzon.cartservice.service.CartService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    private CartService cartService;

    // Add to Cart
    @PostMapping("/add")
    public CartResponse addToCart(@Valid @RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    // View Cart by User
    @GetMapping("/user/{userId}")
    public List<CartResponse> getCartByUser(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    // Update Quantity
    @PutMapping("/{cartId}")
    public CartResponse updateCart(
            @PathVariable Long cartId,
            @RequestParam Integer quantity) {

        return cartService.updateCart(cartId, quantity);
    }

    // Remove Item
    @DeleteMapping("/{cartId}")
    public String removeCartItem(@PathVariable Long cartId) {
        return cartService.removeCartItem(cartId);
    }
}