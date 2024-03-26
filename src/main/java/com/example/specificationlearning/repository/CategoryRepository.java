package com.example.specificationlearning.repository;

import com.example.specificationlearning.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
