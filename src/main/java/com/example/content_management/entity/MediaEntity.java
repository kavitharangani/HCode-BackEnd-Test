package com.example.content_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(columnDefinition = "LONGTEXT")
    private String fileUrl;
    private Date uploadedAt;
}
