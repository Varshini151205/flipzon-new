package com.coforge.training.flipzon.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.productservice.dto.CategoryDTO;
import com.coforge.training.flipzon.productservice.model.Category;
import com.coforge.training.flipzon.productservice.repository.CategoryRepository;
import com.coforge.training.flipzon.productservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO responseDTO = new CategoryDTO();

        BeanUtils.copyProperties(savedCategory, responseDTO);

        return responseDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> {

            CategoryDTO dto = new CategoryDTO();

            BeanUtils.copyProperties(category, dto);

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryDTO dto = new CategoryDTO();

        BeanUtils.copyProperties(category, dto);

        return dto;
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());

        Category updatedCategory = categoryRepository.save(category);

        CategoryDTO dto = new CategoryDTO();

        BeanUtils.copyProperties(updatedCategory, dto);

        return dto;
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

}