package com.coforge.training.flipzon.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.productservice.dto.ProductDTO;
import com.coforge.training.flipzon.productservice.model.Category;
import com.coforge.training.flipzon.productservice.model.Product;
import com.coforge.training.flipzon.productservice.repository.CategoryRepository;
import com.coforge.training.flipzon.productservice.repository.ProductRepository;
import com.coforge.training.flipzon.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // ADD PRODUCT
    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();

        BeanUtils.copyProperties(productDTO, product);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return convertToDTO(savedProduct);
    }

    // GET ALL PRODUCTS
    @Override
    public List<ProductDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET PRODUCT BY ID
    @Override
    public ProductDTO getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return convertToDTO(product);
    }

    // UPDATE PRODUCT
    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setBrand(productDTO.getBrand());
        product.setImageUrl(productDTO.getImageUrl());
        product.setStatus(productDTO.getStatus());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return convertToDTO(updatedProduct);
    }

    // DELETE PRODUCT
    @Override
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    // SEARCH PRODUCTS
    @Override
    public List<ProductDTO> searchProducts(String keyword) {

        List<Product> products =
                productRepository
                        .findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                                keyword,
                                keyword);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // FILTER BY CATEGORY
    @Override
    public List<ProductDTO> filterByCategory(String category) {

        List<Category> categories = categoryRepository.findAll();

        Category selectedCategory = categories.stream()
                .filter(c -> c.getCategoryName().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> products =
                productRepository.findByCategoryCategoryId(
                        selectedCategory.getCategoryId());

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // FILTER BY BRAND
    @Override
    public List<ProductDTO> filterByBrand(String brand) {

        List<Product> products =
                productRepository.findByBrandIgnoreCase(brand);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // FILTER BY PRICE
    @Override
    public List<ProductDTO> filterByPrice(Double minPrice, Double maxPrice) {

        List<Product> products =
                productRepository.findByPriceBetween(minPrice, maxPrice);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // SORT BY NAME
    @Override
    public List<ProductDTO> sortByName(String direction) {

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, "productName");

        List<Product> products = productRepository.findAll(sort);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // SORT BY PRICE
    @Override
    public List<ProductDTO> sortByPrice(String direction) {

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, "price");

        List<Product> products = productRepository.findAll(sort);

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // PAGINATION
    @Override
    public Page<ProductDTO> getProductsByPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage =
                productRepository.findAll(pageable);

        return productPage.map(this::convertToDTO);
    }

    // ENTITY → DTO CONVERSION
    private ProductDTO convertToDTO(Product product) {

        ProductDTO dto = new ProductDTO();

        BeanUtils.copyProperties(product, dto);

        if (product.getCategory() != null) {
            dto.setCategoryId(
                    product.getCategory().getCategoryId());
        }

        return dto;
    }
}