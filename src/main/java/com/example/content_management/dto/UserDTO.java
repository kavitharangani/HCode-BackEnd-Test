package com.example.content_management.dto;

import com.example.content_management.entity.num.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userName;
    private String email;
    private String password;
    private Role role;
}
