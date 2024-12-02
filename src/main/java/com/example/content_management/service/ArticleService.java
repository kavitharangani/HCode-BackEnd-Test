package com.example.content_management.service;
import com.example.content_management.dto.ArticleDTO;
import java.util.List;

public interface ArticleService {

    ArticleDTO saveArticle(ArticleDTO article);

    List<ArticleDTO> getAllArticles();

    void deleteArticle(String id);

    void updateArticle(String authorId, ArticleDTO articleDTO);
}
