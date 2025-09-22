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
import com.pla.springboot.dto.request.BossRequest;
import com.pla.springboot.dto.response.BossResponse;
import com.pla.springboot.service.BossService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
@RequestMapping("/api/bosses")
public class BossController {
    BossService bossService;
    ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    ApiResponse<BossResponse> addBoss(
            @RequestParam("image") MultipartFile image, @RequestParam("bossRequest") String bossRequestString)
            throws JsonMappingException, JsonProcessingException {
        BossRequest bossRequest = objectMapper.readValue(bossRequestString, BossRequest.class);
        return ApiResponse.<BossResponse>builder()
                .result(bossService.addBoss(bossRequest, image))
                .build();
    }

    @GetMapping
    ApiResponse<List<BossResponse>> getBossByCategoryId(@RequestParam Long categoryId) {
        return ApiResponse.<List<BossResponse>>builder()
                .result(bossService.getBossByCategoryId(categoryId))
                .build();
    }
}
