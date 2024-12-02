package com.example.content_management.repo;

import com.example.content_management.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDAO extends JpaRepository<ArticleEntity, String> {
}
