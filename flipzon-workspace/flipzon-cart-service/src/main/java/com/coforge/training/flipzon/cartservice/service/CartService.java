package com.coforge.training.flipzon.cartservice.service;

import java.util.List;

import com.coforge.training.flipzon.cartservice.dto.CartRequest;
import com.coforge.training.flipzon.cartservice.dto.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    List<CartResponse> getCartByUserId(Long userId);

    CartResponse updateCart(Long cartId, Integer quantity);

    String removeCartItem(Long cartId);
}