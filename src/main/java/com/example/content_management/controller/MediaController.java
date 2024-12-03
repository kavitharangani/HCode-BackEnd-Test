package com.example.content_management.controller;

import com.example.content_management.dto.MediaDTO;
import com.example.content_management.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<MediaDTO> uploadMedia(@RequestBody MediaDTO media) {
        return ResponseEntity.ok(mediaService.saveMedia(media));
    }
}
