package com.coforge.training.flipzon.retailerservice.service;

import com.coforge.training.flipzon.retailerservice.dto.RetailerProductDTO;

public interface RetailerProductService {

    RetailerProductDTO addProduct(RetailerProductDTO dto);

    RetailerProductDTO updateProduct(Long id, RetailerProductDTO dto);

    void deleteProduct(Long id);

}