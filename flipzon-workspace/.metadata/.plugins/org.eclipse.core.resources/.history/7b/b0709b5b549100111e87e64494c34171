package com.coforge.training.flipzon.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.flipzon.orderservice.dto.OrderRequest;
import com.coforge.training.flipzon.orderservice.dto.OrderResponse;
import com.coforge.training.flipzon.orderservice.service.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }
}