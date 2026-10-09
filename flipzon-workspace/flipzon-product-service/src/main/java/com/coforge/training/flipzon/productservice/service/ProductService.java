package com.coforge.training.flipzon.productservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coforge.training.flipzon.productservice.dto.ProductDTO;

public interface ProductService {

	ProductDTO addProduct(ProductDTO productDTO);

	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(Long id);

	ProductDTO updateProduct(Long id, ProductDTO productDTO);

	void deleteProduct(Long id);

	List<ProductDTO> searchProducts(String keyword);

	List<ProductDTO> filterByCategory(String category);

	List<ProductDTO> filterByBrand(String brand);

	List<ProductDTO> filterByPrice(Double minPrice, Double maxPrice);

	List<ProductDTO> sortByName(String direction);

	List<ProductDTO> sortByPrice(String direction);

	Page<ProductDTO> getProductsByPage(int page, int size);
}