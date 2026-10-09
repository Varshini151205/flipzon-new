package com.coforge.training.flipzon.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100)
    private String productName;

    @Size(max = 500)
    private String description;

    @NotNull
    @Min(1)
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotBlank
    private String brand;

    private String imageUrl;

    @NotNull
    private Boolean status;

    @NotNull
    private Long categoryId;

}