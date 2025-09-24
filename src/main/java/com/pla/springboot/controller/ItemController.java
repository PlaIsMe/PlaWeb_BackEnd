package com.pla.springboot.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pla.springboot.dto.request.ApiResponse;
import com.pla.springboot.dto.request.ItemRequest;
import com.pla.springboot.dto.response.ItemResponse;
import com.pla.springboot.service.ItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
@RequestMapping("/api/items")
public class ItemController {
    ItemService itemService;
    ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    ApiResponse<ItemResponse> addItem(
            @RequestParam("image") MultipartFile image, @RequestParam("request") String bossRequestString)
            throws JsonMappingException, JsonProcessingException {
        ItemRequest bossRequest = objectMapper.readValue(bossRequestString, ItemRequest.class);
        return ApiResponse.<ItemResponse>builder()
                .result(itemService.addItem(bossRequest, image))
                .build();
    }

    @GetMapping
    ApiResponse<List<ItemResponse>> getItemByCategoryId(@RequestParam Long categoryId) {
        return ApiResponse.<List<ItemResponse>>builder()
                .result(itemService.getBossByCategoryId(categoryId))
                .build();
    }
}
