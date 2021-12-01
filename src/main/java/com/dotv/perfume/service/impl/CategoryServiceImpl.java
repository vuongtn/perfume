package com.dotv.perfume.service.impl;

import com.dotv.perfume.config.ModelMapperConfig;
import com.dotv.perfume.dto.CategoryDTO;
import com.dotv.perfume.entity.Category;
import com.dotv.perfume.repository.CategoryRepository;
import com.dotv.perfume.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryByStatus() {
        return categoryRepository.getCategoryByStatus();
    }

    @Override
    public List<Category> getAllCategory() {
       return categoryRepository.findAll();
    }

    @Override
    public Boolean addCategory(Category category) {
        if(categoryRepository.save(category)!=null)
            return true;
        return false;
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
