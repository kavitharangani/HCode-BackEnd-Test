package com.example.content_management.service;
import com.example.content_management.dto.MediaDTO;

import java.util.List;


public interface MediaService {
    MediaDTO saveMedia(MediaDTO media);
    void deleteMediaById(String id);
    MediaDTO updateMedia(String id, MediaDTO media);
    MediaDTO getMediaById(String id);
    List<MediaDTO> getAllMedia();
}
