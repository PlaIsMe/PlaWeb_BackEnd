package com.pla.springboot.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pla.springboot.dto.request.BossRequest;
import com.pla.springboot.dto.response.BossResponse;
import com.pla.springboot.entity.Boss;
import com.pla.springboot.entity.Category;
import com.pla.springboot.exception.AppException;
import com.pla.springboot.exception.ErrorCode;
import com.pla.springboot.mapper.BossMapper;
import com.pla.springboot.repository.BossRepository;
import com.pla.springboot.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackFor = Exception.class)
public class BossService {
    BossRepository bossRepository;
    CategoryRepository categoryRepository;
    BossMapper bossMapper;
    CloudinaryService cloudinaryService;

    public BossResponse addBoss(BossRequest request, MultipartFile image) {
        System.out.println("ADD BOSS");
        if (bossRepository.existsById(request.getId())) throw new AppException(ErrorCode.BOSS_ID_EXISTED);

        Boss boss = bossMapper.toBoss(request);
        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        try {
            boss.setImageUrl(cloudinaryService.uploadImage(image));
        } catch (IOException e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }
        boss.setCategory(category);

        return bossMapper.toBossResponse(bossRepository.save(boss));
    }

    public List<BossResponse> getBossByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return bossRepository.findByCategory_Id(categoryId).stream()
                .map(boss -> bossMapper.toBossResponse(boss))
                .collect(Collectors.toList());
    }
}
