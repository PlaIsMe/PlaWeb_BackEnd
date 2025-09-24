package com.pla.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pla.springboot.dto.request.ItemRequest;
import com.pla.springboot.dto.response.ItemResponse;
import com.pla.springboot.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemRequest request);

    @Mapping(target = "categoryName", source = "category.name")
    ItemResponse toItemResponse(Item item);
}
