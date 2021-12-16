package com.dotv.perfume.service;

import com.dotv.perfume.dto.CategoryDTO;
import com.dotv.perfume.entity.Category;
import com.dotv.perfume.untils.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategoryByStatus();
    List<Category> getAllCategory();
    MessageResponse addCategory(CategoryDTO categoryDTO);
    Optional<Category> getCategoryById(int id);
}
