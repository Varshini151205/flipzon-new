package com.coforge.training.flipzon.cartservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.flipzon.cartservice.dto.ProductDTO;

@FeignClient(name = "FLIPZON-PRODUCT-SERVICE")
public interface ProductFeignClient {

    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

}