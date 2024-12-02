package com.example.content_management.dto;

import com.example.content_management.entity.num.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArticleDTO {
    private String id; // Match the type with ArticleEntity
    private String authorId; // The ID of the author (user)
    private String title;
    private String content;
    private ArticleStatus status; // Enum for Draft or Published
    private Date publishedAt;
    private Date createdAt;
    private Date updatedAt;
}
