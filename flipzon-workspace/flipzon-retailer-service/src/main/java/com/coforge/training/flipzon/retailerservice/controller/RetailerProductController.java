package com.coforge.training.flipzon.retailerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.flipzon.retailerservice.dto.RetailerProductDTO;
import com.coforge.training.flipzon.retailerservice.service.RetailerProductService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/retailer/products")
public class RetailerProductController {

    private final RetailerProductService service;

    public RetailerProductController(RetailerProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RetailerProductDTO> addProduct(
            @Valid @RequestBody RetailerProductDTO dto) {

        return new ResponseEntity<>(service.addProduct(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetailerProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody RetailerProductDTO dto) {

        return ResponseEntity.ok(service.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.ok("Retailer Product deleted successfully.");

    }

}