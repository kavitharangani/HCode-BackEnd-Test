package com.example.content_management.service;

import com.example.content_management.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDTO save(UserDTO user);
    UserDTO login(UserDTO user);

    Object userDetailService();

}
