package com.coforge.training.flipzon.retailerservice.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "retailer_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailerProductId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long retailerId;

}