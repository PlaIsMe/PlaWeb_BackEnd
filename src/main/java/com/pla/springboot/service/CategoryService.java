package com.pla.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (categoryRepository.existsByName(request.getName())) throw new AppException(ErrorCode.CATEGORY_NAME_EXISTED);

        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public Long count(Map<String, String> params) {
        String kw;
        if (params != null) {
            kw = params.get("kw");
            if (kw == null || kw.isEmpty()) {
                kw = "_";
            } else {
                kw = kw.trim();
            }
        } else {
            kw = "_";
        }
        return categoryRepository.count(kw);
    }

    public List<CategoryResponse> search(Map<String, String> params) {
        int page;
        String kw;
        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                page = Integer.parseInt(p) - 1;
            } else {
                page = 0;
            }

            kw = params.get("kw");
            if (kw == null || kw.isEmpty()) {
                kw = "_";
            } else {
                kw = kw.trim();
            }

        } else {
            page = 0;
            kw = "_";
        }

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "id"));
        return categoryRepository.search(kw, pageable).stream()
                .map(category -> categoryMapper.toCategoryResponse(category))
                .collect(Collectors.toList());
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
