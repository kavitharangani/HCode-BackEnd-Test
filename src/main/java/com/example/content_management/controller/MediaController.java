package com.example.content_management.controller;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.dto.MediaDTO;
import com.example.content_management.service.ArticleService;
import com.example.content_management.service.MediaService;
import com.example.content_management.util.UtilMatters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class MediaController {

    private final MediaService mediaService;

    // Sets the HTTP status to 201 CREATED when the resource is successfully created
    @ResponseStatus(HttpStatus.CREATED)
    // Handles POST requests with multipart/form-data content type
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public MediaDTO saveMedia(@Valid
                              @RequestPart("id") String id,
                              @RequestPart("fileUrl") MultipartFile fileUrl,
                              @RequestPart("fileName") String fileName,
                              @RequestPart("uploadedAt") String uploadedAt,
                              Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }

        // Convert MultipartFile to Base64 string
        String base64ProPic;
        try {
            base64ProPic = UtilMatters.convertBase64(String.valueOf(fileUrl.getBytes()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing file upload", e);
        }


        MediaDTO buildMedia = new MediaDTO();
        buildMedia.setId(id);
        buildMedia.setFileUrl(base64ProPic);
        buildMedia.setFileName(fileName);
        buildMedia.setUploadedAt(Date.valueOf(uploadedAt));

        return mediaService.saveMedia(buildMedia);
    }
}
