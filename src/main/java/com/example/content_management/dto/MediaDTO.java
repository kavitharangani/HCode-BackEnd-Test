package com.example.content_management.dto;

import com.example.content_management.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MediaDTO {
    private String id;
    private UserEntity user;
    private String fileName;
    private String fileUrl;
    private LocalDateTime uploadedAt;
}
