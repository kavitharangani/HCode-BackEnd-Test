package com.example.content_management.service;
import com.example.content_management.dto.ArticleDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {

    ArticleDTO saveArticle(ArticleDTO article);

    List<ArticleDTO> getAllArticles();

    void deleteArticle(String id);

    ResponseEntity<?> updateArticle(String authorId, ArticleDTO articleDTO)throws ChangeSetPersister.NotFoundException;
}
