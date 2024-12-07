package com.example.content_management.service.impl;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.dto.MediaDTO;
import com.example.content_management.entity.ArticleEntity;
import com.example.content_management.entity.MediaEntity;
import com.example.content_management.repo.ArticleDAO;
import com.example.content_management.repo.MediaDAO;
import com.example.content_management.service.ArticleService;
import com.example.content_management.service.MediaService;
import com.example.content_management.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaServiceIMPL implements MediaService {

    private final MediaDAO mediaDAO;
    private final Mapping mapping;

    @Override
    public MediaDTO saveMedia(MediaDTO media) {
        return mapping.toMediaDTO(mediaDAO.save(mapping.toMedia(media)));
    }
    @Override
    public void deleteMediaById(String id) {
        mediaDAO.deleteById(id);
    }

    @Override
    public MediaDTO updateMedia(String id, MediaDTO media) {
        Optional<MediaEntity> tmpMediaOptional = mediaDAO.findById(id);

        if (tmpMediaOptional.isPresent()) {
            MediaEntity tmpMedia = tmpMediaOptional.get();

            // Update the fields
            tmpMedia.setFileName(media.getFileName());
            tmpMedia.setFileUrl(media.getFileUrl());
            tmpMedia.setUploadedAt(media.getUploadedAt());

            // Save the updated entity
            MediaEntity updatedMedia = mediaDAO.save(tmpMedia);

            // Map and return the DTO
            return mapping.toMediaDTO(updatedMedia);
        } else {
            throw new EntityNotFoundException("Media with ID " + id + " not found");
        }
    }


    @Override
    public MediaDTO getMediaById(String id) {
        return mediaDAO.findById(id)
                .map(mapping::toMediaDTO)
                .orElseThrow(() -> new IllegalArgumentException("Media with ID " + id + " not found."));
    }

    @Override
    public List<MediaDTO> getAllMedia() {
        return mediaDAO.findAll().stream()
                .map(mapping::toMediaDTO)
                .collect(Collectors.toList());
    }
}
