package com.example.content_management.service.impl;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.entity.ArticleEntity;
import com.example.content_management.repo.ArticleDAO;
import com.example.content_management.service.ArticleService;
import com.example.content_management.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceIMPL implements ArticleService {

    private final ArticleDAO articleDAO;
    private final Mapping mapping;

    @Override
    public ArticleDTO saveArticle(ArticleDTO article) {
        article.setId(UUID.randomUUID().toString());
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
    public void updateArticle(String authorId, ArticleDTO articleDTO) {
        ArticleEntity tmpArticle = articleDAO.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        tmpArticle.setAuthorId(articleDTO.getAuthorId());
        tmpArticle.setTitle(articleDTO.getTitle());
        tmpArticle.setContent(articleDTO.getContent());

        articleDAO.save(tmpArticle);
    }
}
