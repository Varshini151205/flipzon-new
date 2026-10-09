package com.coforge.training.flipzon.productservice.service;

import java.util.List;

import com.coforge.training.flipzon.productservice.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);

}