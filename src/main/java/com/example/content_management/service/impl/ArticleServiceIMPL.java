package com.example.content_management.service.impl;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.entity.ArticleEntity;
import com.example.content_management.repo.ArticleDAO;
import com.example.content_management.service.ArticleService;
import com.example.content_management.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceIMPL implements ArticleService {

    private final ArticleDAO articleDAO;
    private final Mapping mapping;

    @Override
    public ArticleDTO saveArticle(ArticleDTO article) {
        return mapping.toArticleDTO(articleDAO.save(mapping.toArticle(article)));
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleDAO.findAll()
                .stream()
                .map(mapping::toArticleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(String id) {
        if (!articleDAO.existsById(id)) {
            throw new RuntimeException("Article not found");
        }
        articleDAO.deleteById(id);
    }

    @Override
    public ResponseEntity<?> updateArticle(String authorId, ArticleDTO articleDTO) {
        // Fetch the article by the given authorId
        ArticleEntity tmpArticle = articleDAO.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Update the fields of the article entity with data from the DTO
        tmpArticle.setAuthorId(articleDTO.getAuthorId());
        tmpArticle.setTitle(articleDTO.getTitle());
        tmpArticle.setContent(articleDTO.getContent());

        // Save the updated article back to the database
        articleDAO.save(tmpArticle);

        // Return a successful response (you can return the updated article as a response body if needed)
        return ResponseEntity.ok(tmpArticle);
    }

}
