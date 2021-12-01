package com.dotv.perfume.service;

import com.dotv.perfume.dto.CategoryDTO;
import com.dotv.perfume.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategoryByStatus();
    List<Category> getAllCategory();
    Boolean addCategory(Category category);
    Optional<Category> getCategoryById(int id);
}
