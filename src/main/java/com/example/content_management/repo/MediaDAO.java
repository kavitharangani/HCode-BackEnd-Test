package com.example.content_management.repo;

import com.example.content_management.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaDAO extends JpaRepository<MediaEntity, String> {
    List<MediaEntity> findByUserId(String id);
}
