package com.coforge.training.flipzon.retailerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.training.flipzon.retailerservice.model.RetailerProduct;

@Repository
public interface RetailerProductRepository extends JpaRepository<RetailerProduct, Long> {

}