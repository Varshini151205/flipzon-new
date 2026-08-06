package com.coforge.training.flipzon.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalAmount;
    private String status;

}