package com.taskmaster.controller;

import com.taskmaster.dto.UserUpdateRequest;
import com.taskmaster.model.User;
import com.taskmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateCurrentUser(Authentication authentication, @RequestBody UserUpdateRequest request) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());

        return ResponseEntity.ok(userRepository.save(user));
    }
}
