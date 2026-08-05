package com.taskmaster.controller;

import com.taskmaster.model.*;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.service.CollaborationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks/{taskId}")
public class CollaborationController {

    private final CollaborationService collaborationService;
    private final UserRepository userRepository;

    public CollaborationController(CollaborationService collaborationService, UserRepository userRepository) {
        this.collaborationService = collaborationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@PathVariable UUID taskId, Authentication authentication, @RequestBody String content) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(collaborationService.addComment(taskId, user, content));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable UUID taskId) {
        return ResponseEntity.ok(collaborationService.getCommentsByTask(taskId));
    }

    @PostMapping("/attachments")
    public ResponseEntity<Attachment> addAttachment(@PathVariable UUID taskId, Authentication authentication, @RequestParam("file") MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(collaborationService.addAttachment(taskId, user, file));
    }

    @GetMapping("/attachments")
    public ResponseEntity<List<Attachment>> getAttachments(@PathVariable UUID taskId) {
        return ResponseEntity.ok(collaborationService.getAttachmentsByTask(taskId));
    }
}
