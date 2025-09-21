package com.pla.springboot.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BossResponse {
    String id;
    String name;
    String imageUrl;
    String description;
    String youtubeId;
    String categoryName;
}
