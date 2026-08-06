package com.coforge.training.flipzon.adminservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.flipzon.adminservice.dto.OrderDTO;

@FeignClient(name = "FLIPZON-ORDER-SERVICE")
public interface OrderFeignClient {

    @GetMapping("/orders/user/{userId}")
    List<OrderDTO> getOrdersByUser(@PathVariable Long userId);

    @GetMapping("/orders/{orderId}")
    OrderDTO getOrderById(@PathVariable Long orderId);

}