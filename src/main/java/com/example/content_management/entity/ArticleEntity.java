package com.example.content_management.entity;

import com.example.content_management.entity.num.ArticleStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    private String id;
    private String authorId;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;
    private Date publishedAt;
    private Date createdAt;
    private Date updatedAt;

}
