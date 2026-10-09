package com.coforge.training.flipzon.retailerservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.retailerservice.dto.RetailerProductDTO;
import com.coforge.training.flipzon.retailerservice.model.RetailerProduct;
import com.coforge.training.flipzon.retailerservice.repository.RetailerProductRepository;
import com.coforge.training.flipzon.retailerservice.service.RetailerProductService;

@Service
public class RetailerProductServiceImpl implements RetailerProductService {

    private final RetailerProductRepository repository;

    public RetailerProductServiceImpl(RetailerProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetailerProductDTO addProduct(RetailerProductDTO dto) {

        RetailerProduct entity = new RetailerProduct();

        BeanUtils.copyProperties(dto, entity);

        entity = repository.save(entity);

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public RetailerProductDTO updateProduct(Long id, RetailerProductDTO dto) {

        RetailerProduct entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retailer Product not found"));

        entity.setProductId(dto.getProductId());
        entity.setRetailerId(dto.getRetailerId());

        entity = repository.save(entity);

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public void deleteProduct(Long id) {

        repository.deleteById(id);

    }

}