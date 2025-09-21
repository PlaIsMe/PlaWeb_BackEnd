package com.pla.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Boss {
    @Id
    @Column(length = 64)
    private String id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @Lob
    private String description;

    private String youtubeId;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
