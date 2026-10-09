package com.coforge.training.flipzon.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.training.flipzon.productservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByBrandContainingIgnoreCase(String brand);

    List<Product> findByCategoryCategoryId(Long categoryId);
    
    List<Product> findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String productName, String description);
    
    List<Product> findByBrandIgnoreCase(String brand);
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}