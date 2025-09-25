package com.pla.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pla.springboot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    Optional<Category> findByName(String name);

    @Query("SELECT c FROM Category c " +
            "WHERE c.name " +
            "LIKE %?1% ")
    List<Category> search(String kw, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Category c " +
            "WHERE c.name " +
            "LIKE %?1% ")
    Long count(String kw);
}
