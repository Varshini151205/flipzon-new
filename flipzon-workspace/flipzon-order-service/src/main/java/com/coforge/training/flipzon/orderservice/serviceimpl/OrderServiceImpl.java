package com.coforge.training.flipzon.orderservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.orderservice.dto.OrderRequest;
import com.coforge.training.flipzon.orderservice.dto.OrderResponse;
import com.coforge.training.flipzon.orderservice.dto.ProductDTO;
import com.coforge.training.flipzon.orderservice.entity.Order;
import com.coforge.training.flipzon.orderservice.feign.ProductFeignClient;
import com.coforge.training.flipzon.orderservice.feign.UserFeignClient;
import com.coforge.training.flipzon.orderservice.repository.OrderRepository;
import com.coforge.training.flipzon.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        // Verify user exists
        userFeignClient.getUserById(request.getUserId());

        // Verify product exists
        ProductDTO product = productFeignClient.getProductById(request.getProductId());

        Order order = new Order();
        BeanUtils.copyProperties(request, order);

        order.setTotalAmount(product.getPrice() * request.getQuantity());
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(savedOrder, response);

        return response;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        List<OrderResponse> responses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse response = new OrderResponse();
            BeanUtils.copyProperties(order, response);
            responses.add(response);
        }

        return responses;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);

        return response;
    }

    @Override
    public String cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return "Order Cancelled Successfully";
    }
}