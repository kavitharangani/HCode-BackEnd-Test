package com.example.content_management.controller;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.dto.UserDTO;
import com.example.content_management.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/health")
    public String healthTest() {
        return "Articles Health work";
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTO article) {
        ArticleDTO savedArticle = articleService.saveArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @DeleteMapping(value = "/{authorId}")
    public ResponseEntity<?> deleteArticle(@PathVariable("authorId") String id) {
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.ok("Article deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{authorId}")
    public ResponseEntity<?> update(@PathVariable String authorId, @RequestBody ArticleDTO articleDTO) throws ChangeSetPersister.NotFoundException {
        // Ensure the authorId in the URL matches the one in the DTO (optional)
        if (!authorId.equals(articleDTO.getAuthorId())) {
            return ResponseEntity.badRequest().body("Author ID mismatch");
        }

        return articleService.updateArticle(authorId, articleDTO);
    }

}
