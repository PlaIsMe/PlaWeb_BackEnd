package com.pla.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pla.springboot.dto.request.CategoryRequest;
import com.pla.springboot.dto.response.CategoryResponse;
import com.pla.springboot.entity.Category;
import com.pla.springboot.exception.AppException;
import com.pla.springboot.exception.ErrorCode;
import com.pla.springboot.mapper.CategoryMapper;
import com.pla.springboot.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackFor = Exception.class)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse addCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) throw new AppException(ErrorCode.ITEM_ID_EXISTED);

        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.toCategoryResponse(category))
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .map(categoryMapper::toCategoryResponse)
                .get();
    }
}
