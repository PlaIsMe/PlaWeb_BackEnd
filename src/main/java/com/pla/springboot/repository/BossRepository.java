package com.pla.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pla.springboot.entity.Boss;

@Repository
public interface BossRepository extends JpaRepository<Boss, String> {
    List<Boss> findByCategory_Id(Long categoryId);
}
