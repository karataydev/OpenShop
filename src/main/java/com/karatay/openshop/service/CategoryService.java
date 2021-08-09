package com.karatay.openshop.service;

import com.karatay.openshop.dto.CategoryDto;
import com.karatay.openshop.model.Category;
import com.karatay.openshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getCategories(){
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDto(c.getName(), c.getPictureUrl()))
                .collect(Collectors.toList());
    }
}
