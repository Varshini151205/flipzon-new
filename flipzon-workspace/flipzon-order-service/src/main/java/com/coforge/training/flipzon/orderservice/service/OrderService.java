package com.coforge.training.flipzon.orderservice.service;

import java.util.List;

import com.coforge.training.flipzon.orderservice.dto.OrderRequest;
import com.coforge.training.flipzon.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse getOrderById(Long orderId);

    String cancelOrder(Long orderId);
}