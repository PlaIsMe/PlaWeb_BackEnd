package com.pla.springboot.mapper;

import org.mapstruct.Mapper;

import com.pla.springboot.dto.request.CategoryRequest;
import com.pla.springboot.dto.response.CategoryResponse;
import com.pla.springboot.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
