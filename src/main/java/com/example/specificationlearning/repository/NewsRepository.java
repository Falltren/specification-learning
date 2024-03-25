package com.example.specificationlearning.repository;

import com.example.specificationlearning.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
