package com.pla.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pla.springboot.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByCategory_Id(Long categoryId);
}
