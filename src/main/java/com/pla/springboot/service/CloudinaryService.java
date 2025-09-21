package com.pla.springboot.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pla.springboot.utils.CloudinaryUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryService {
    Cloudinary cloudinary;

    public String uploadImage(MultipartFile image) throws IOException {
        return cloudinary
                .uploader()
                .upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"))
                .get("secure_url")
                .toString();
    }

    public void deleteImage(String imageUrl) throws IOException {
        Map<String, String> response =
                cloudinary.uploader().destroy(CloudinaryUtils.getPublicId(imageUrl), ObjectUtils.emptyMap());
        System.out.println("Delete image: " + imageUrl + " status: " + response);
    }
}
