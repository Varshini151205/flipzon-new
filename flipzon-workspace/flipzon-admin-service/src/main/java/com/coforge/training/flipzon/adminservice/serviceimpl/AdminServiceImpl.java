package com.coforge.training.flipzon.adminservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.adminservice.dto.OrderDTO;
import com.coforge.training.flipzon.adminservice.dto.ProductDTO;
import com.coforge.training.flipzon.adminservice.dto.UserDTO;
import com.coforge.training.flipzon.adminservice.feign.OrderFeignClient;
import com.coforge.training.flipzon.adminservice.feign.ProductFeignClient;
import com.coforge.training.flipzon.adminservice.feign.UserFeignClient;
import com.coforge.training.flipzon.adminservice.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    public UserDTO getUserById(Long userId) {
        return userFeignClient.getUserById(userId);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productFeignClient.getAllProducts();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return productFeignClient.getProductById(productId);
    }

    @Override
    public String deleteProduct(Long productId) {
        return productFeignClient.deleteProduct(productId);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderFeignClient.getOrderById(orderId);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderFeignClient.getOrdersByUser(userId);
    }
}