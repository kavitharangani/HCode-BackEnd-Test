package com.example.content_management.util;

import com.example.content_management.dto.ArticleDTO;
import com.example.content_management.dto.UserDTO;
import com.example.content_management.entity.ArticleEntity;
import com.example.content_management.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;

    public UserDTO toUserDTO(UserEntity user) {
        return mapper.map(user, UserDTO.class);
    }

    public UserEntity toUserEntity(UserDTO userDTO) {
        return mapper.map(userDTO, UserEntity.class);
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> users) {
        return mapper.map(users, List.class);
    }

    /////////Article////////

    public ArticleDTO toArticleDTO(ArticleEntity article) {
        return  mapper.map(article, ArticleDTO.class);
    }
    public ArticleEntity toArticle(ArticleDTO articleDTO) {
        return  mapper.map(articleDTO, ArticleEntity.class);
    }
    public List<ArticleDTO> toArticleDTOList(List<ArticleEntity> article) {
        return mapper.map(article, List.class);
    }

}
