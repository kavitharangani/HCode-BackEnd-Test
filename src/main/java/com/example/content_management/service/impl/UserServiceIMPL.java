package com.example.content_management.service.impl;

import com.example.content_management.dto.UserDTO;
import com.example.content_management.entity.UserEntity;
import com.example.content_management.repo.UserDAO;
import com.example.content_management.service.UserService;
import com.example.content_management.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserDAO userRepo;
    private final Mapping mapping;
    private final PasswordEncoder passwordEncoder;

    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}"; // Min 8 characters, 1 digit, 1 lowercase, 1 uppercase

    @Override
    public UserDTO save(UserDTO user) {
        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Check if user already exists
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        // Validate password strength
        if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain a mix of uppercase, lowercase, and numbers");
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(mapping.toUserEntity(user));
        return user;
    }

    @Override
    public UserDTO login(UserDTO user) {
        UserEntity foundUser = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return mapping.toUserDTO(foundUser); // Return user DTO upon successful login
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public Object userDetailService() {
        return null;
    }

    private boolean isValidEmail(String email) {
        // Simple email validation (you can use more sophisticated regex or external validators)
        return email != null && email.contains("@") && email.contains(".");
    }
}
