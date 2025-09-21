package com.pla.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pla.springboot.dto.request.BossRequest;
import com.pla.springboot.dto.response.BossResponse;
import com.pla.springboot.entity.Boss;

@Mapper(componentModel = "spring")
public interface BossMapper {
    Boss toBoss(BossRequest request);

    @Mapping(target = "categoryName", source = "category.name")
    BossResponse toBossResponse(Boss boss);
}
