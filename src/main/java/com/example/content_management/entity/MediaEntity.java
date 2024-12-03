package com.example.content_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "media")
public class MediaEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String fileName;
    private String fileUrl;
    private LocalDateTime uploadedAt;
}
