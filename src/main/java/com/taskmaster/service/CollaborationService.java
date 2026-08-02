package com.taskmaster.service;

import com.taskmaster.exception.ResourceNotFoundException;
import com.taskmaster.model.*;
import com.taskmaster.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollaborationService {

    private final CommentRepository commentRepository;
    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment addComment(UUID taskId, User user, String content) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        Comment comment = Comment.builder()
                .content(content)
                .task(task)
                .user(user)
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(UUID taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    @Transactional
    public Attachment addAttachment(UUID taskId, User user, MultipartFile file) throws IOException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        // Simple local storage implementation for demonstration
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .fileUrl(filePath.toString())
                .fileType(file.getContentType())
                .task(task)
                .user(user)
                .build();

        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getAttachmentsByTask(UUID taskId) {
        return attachmentRepository.findByTaskId(taskId);
    }
}
