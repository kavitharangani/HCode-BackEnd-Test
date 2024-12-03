package com.example.content_management.service.impl;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.dto.MediaDTO;
import com.example.content_management.entity.ArticleEntity;
import com.example.content_management.repo.ArticleDAO;
import com.example.content_management.repo.MediaDAO;
import com.example.content_management.service.ArticleService;
import com.example.content_management.service.MediaService;
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
public class MediaServiceIMPL implements MediaService {

    private final MediaDAO mediaDAO;
    private final Mapping mapping;

    @Override
    public MediaDTO saveMedia(MediaDTO media) {
        return mapping.toMediaDTO(mediaDAO.save(mapping.toMedia(media)));
    }
}
