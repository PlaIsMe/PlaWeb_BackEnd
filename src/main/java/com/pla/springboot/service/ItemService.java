package com.pla.springboot.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pla.springboot.dto.request.ItemRequest;
import com.pla.springboot.dto.response.ItemResponse;
import com.pla.springboot.entity.Category;
import com.pla.springboot.entity.Item;
import com.pla.springboot.exception.AppException;
import com.pla.springboot.exception.ErrorCode;
import com.pla.springboot.mapper.ItemMapper;
import com.pla.springboot.repository.CategoryRepository;
import com.pla.springboot.repository.ItemRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackFor = Exception.class)
public class ItemService {
    ItemRepository itemRepository;
    CategoryRepository categoryRepository;
    ItemMapper itemMapper;
    CloudinaryService cloudinaryService;

    public ItemResponse addItem(ItemRequest request, MultipartFile image) {
        if (itemRepository.existsById(request.getId())) throw new AppException(ErrorCode.ITEM_ID_EXISTED);

        Item item = itemMapper.toItem(request);
        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        try {
            item.setImageUrl(cloudinaryService.uploadImage(image));
        } catch (IOException e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }
        item.setCategory(category);

        return itemMapper.toItemResponse(itemRepository.save(item));
    }

    public List<ItemResponse> getBossByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return itemRepository.findByCategory_Id(categoryId).stream()
                .map(item -> itemMapper.toItemResponse(item))
                .collect(Collectors.toList());
    }
}
