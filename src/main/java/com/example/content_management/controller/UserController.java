package com.example.content_management.controller;

import com.example.content_management.dto.UserDTO;
import com.example.content_management.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:63342")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
        try {
            UserDTO savedUser = userService.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO, HttpSession session) {
        try {
            UserDTO loggedInUser = userService.login(userDTO);

            // Set user details in the session
            session.setAttribute("loggedInUser", loggedInUser);

            return ResponseEntity.ok(loggedInUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate(); // Clear the session
        return ResponseEntity.ok("Logged out successfully");
    }
}
