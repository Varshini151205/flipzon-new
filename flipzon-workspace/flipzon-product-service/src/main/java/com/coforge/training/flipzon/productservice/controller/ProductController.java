package com.coforge.training.flipzon.productservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.coforge.training.flipzon.productservice.dto.ProductDTO;
import com.coforge.training.flipzon.productservice.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // =========================================================
    // Add Product - Existing JSON Endpoint
    // =========================================================

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(
            @Valid @RequestBody ProductDTO productDTO) {

        ProductDTO savedProduct = productService.addProduct(productDTO);

        return new ResponseEntity<>(
                savedProduct,
                HttpStatus.CREATED
        );
    }


    // =========================================================
    // Add Product With Image
    // =========================================================

    @PostMapping(
            value = "/with-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> addProductWithImage(

            @RequestParam("productName")
            String productName,

            @RequestParam("description")
            String description,

            @RequestParam("price")
            Double price,

            @RequestParam("stock")
            Integer stock,

            @RequestParam("brand")
            String brand,

            @RequestParam("status")
            Boolean status,

            @RequestParam("categoryId")
            Long categoryId,

            @RequestParam("image")
            MultipartFile image) {

        try {

            // -------------------------------------------------
            // Check Image
            // -------------------------------------------------

            if (image == null || image.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("Please select a product image.");
            }


            // -------------------------------------------------
            // Check Image Type
            // -------------------------------------------------

            String contentType = image.getContentType();

            if (contentType == null ||
                    !(contentType.equals("image/jpeg")
                    || contentType.equals("image/png")
                    || contentType.equals("image/jpg")
                    || contentType.equals("image/webp"))) {

                return ResponseEntity
                        .badRequest()
                        .body(
                            "Only JPG, JPEG, PNG and WEBP images are allowed."
                        );
            }


            // -------------------------------------------------
            // Create Upload Folder
            // -------------------------------------------------

            Path uploadPath =
                    Paths.get("uploads/products");

            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);
            }


            // -------------------------------------------------
            // Get File Extension
            // -------------------------------------------------

            String originalFileName =
                    image.getOriginalFilename();

            String extension = "";

            if (originalFileName != null &&
                    originalFileName.contains(".")) {

                extension =
                        originalFileName.substring(
                            originalFileName.lastIndexOf(".")
                        );
            }


            // -------------------------------------------------
            // Generate Unique File Name
            // -------------------------------------------------

            String fileName =
                    UUID.randomUUID().toString()
                    + extension;


            // -------------------------------------------------
            // Save Image
            // -------------------------------------------------

            Path filePath =
                    uploadPath.resolve(fileName);

            Files.copy(
                    image.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            // -------------------------------------------------
            // Create ProductDTO
            // -------------------------------------------------

            ProductDTO productDTO =
                    new ProductDTO();

            productDTO.setProductName(productName);
            productDTO.setDescription(description);
            productDTO.setPrice(price);
            productDTO.setStock(stock);
            productDTO.setBrand(brand);
            productDTO.setStatus(status);
            productDTO.setCategoryId(categoryId);


            // -------------------------------------------------
            // Save Image Path in Database
            // -------------------------------------------------

            productDTO.setImageUrl(
                    "/api/products/images/" + fileName
            );


            // -------------------------------------------------
            // Save Product
            // -------------------------------------------------

            ProductDTO savedProduct =
                    productService.addProduct(productDTO);


            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedProduct);


        } catch (IOException e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                        "Failed to upload image: "
                        + e.getMessage()
                    );
        }
    }


    // =========================================================
    // Get Product Image
    // =========================================================

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getProductImage(
            @PathVariable String fileName) {

        try {

            Path filePath =
                    Paths.get("uploads/products")
                            .resolve(fileName)
                            .normalize();


            Resource resource =
                    new UrlResource(
                            filePath.toUri()
                    );


            if (!resource.exists()) {

                return ResponseEntity
                        .notFound()
                        .build();
            }


            String contentType =
                    Files.probeContentType(filePath);


            if (contentType == null) {

                contentType =
                        "application/octet-stream";
            }


            return ResponseEntity
                    .ok()
                    .contentType(
                        MediaType.parseMediaType(
                            contentType
                        )
                    )
                    .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\""
                        + resource.getFilename()
                        + "\""
                    )
                    .body(resource);


        } catch (Exception e) {

            return ResponseEntity
                    .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .build();
        }
    }


    // =========================================================
    // Get All Products
    // =========================================================

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }


    // =========================================================
    // Get Product By Id
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }


    // =========================================================
    // Update Product
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {

        return ResponseEntity.ok(
                productService.updateProduct(
                        id,
                        productDTO
                )
        );
    }


    // =========================================================
    // Delete Product
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                "Product deleted successfully."
        );
    }


    // =========================================================
    // Search Products
    // =========================================================

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                productService.searchProducts(keyword)
        );
    }


    // =========================================================
    // Filter By Category
    // =========================================================

    @GetMapping("/filter/category")
    public ResponseEntity<List<ProductDTO>> filterByCategory(
            @RequestParam String category) {

        return ResponseEntity.ok(
                productService.filterByCategory(category)
        );
    }


    // =========================================================
    // Filter By Brand
    // =========================================================

    @GetMapping("/filter/brand")
    public ResponseEntity<List<ProductDTO>> filterByBrand(
            @RequestParam String brand) {

        return ResponseEntity.ok(
                productService.filterByBrand(brand)
        );
    }


    // =========================================================
    // Filter By Price
    // =========================================================

    @GetMapping("/filter/price")
    public ResponseEntity<List<ProductDTO>> filterByPrice(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {

        return ResponseEntity.ok(
                productService.filterByPrice(
                        minPrice,
                        maxPrice
                )
        );
    }


    // =========================================================
    // Sort By Name
    // =========================================================

    @GetMapping("/sort/name")
    public ResponseEntity<List<ProductDTO>> sortByName(
            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(
                productService.sortByName(direction)
        );
    }


    // =========================================================
    // Sort By Price
    // =========================================================

    @GetMapping("/sort/price")
    public ResponseEntity<List<ProductDTO>> sortByPrice(
            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(
                productService.sortByPrice(direction)
        );
    }


    // =========================================================
    // Pagination
    // =========================================================

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> getProductsByPage(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "8")
            int size) {

        return ResponseEntity.ok(
                productService.getProductsByPage(
                        page,
                        size
                )
        );
    }
}