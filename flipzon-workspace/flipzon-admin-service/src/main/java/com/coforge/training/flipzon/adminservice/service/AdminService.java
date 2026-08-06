package com.coforge.training.flipzon.adminservice.service;

import java.util.List;

import com.coforge.training.flipzon.adminservice.dto.OrderDTO;
import com.coforge.training.flipzon.adminservice.dto.ProductDTO;
import com.coforge.training.flipzon.adminservice.dto.UserDTO;

public interface AdminService {

    UserDTO getUserById(Long userId);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long productId);

    String deleteProduct(Long productId);

    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getOrdersByUser(Long userId);

}