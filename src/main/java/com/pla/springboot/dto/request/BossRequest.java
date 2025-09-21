package com.pla.springboot.dto.request;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BossRequest {
    @Size(min = 3, message = "BOSS_ID_INVALID")
    String id;

    @Size(min = 3, message = "BOSS_NAME_INVALID")
    String name;

    @Lob
    private String description;

    private String youtubeId;

    @NotBlank(message = "CATEGORY_ID_REQUIRED")
    private Long categoryId;
}
