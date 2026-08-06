package com.coforge.training.flipzon.adminservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.flipzon.adminservice.dto.ProductDTO;

@FeignClient(name = "FLIPZON-PRODUCT-SERVICE")
public interface ProductFeignClient {

    @GetMapping("/api/products")
    List<ProductDTO> getAllProducts();

    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    @DeleteMapping("/api/products/{id}")
    String deleteProduct(@PathVariable Long id);

}