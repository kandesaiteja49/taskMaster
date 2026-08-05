package com.taskmaster.controller;

import com.taskmaster.dto.TaskRequest;
import com.taskmaster.model.*;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.repository.TeamRepository;
import com.taskmaster.service.TaskService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final com.taskmaster.service.AiTaskService aiTaskService;

    public TaskController(TaskService taskService, UserRepository userRepository, TeamRepository teamRepository, com.taskmaster.service.AiTaskService aiTaskService) {
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.aiTaskService = aiTaskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(Authentication authentication, @RequestBody TaskRequest request) {
        User creator = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .priority(request.getPriority())
                .build();

        if (request.getAssigneeId() != null) {
            User assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            task.setAssignee(assignee);
        }

        return ResponseEntity.ok(taskService.createTask(task, creator, team));
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(required = false) UUID assigneeId,
            @RequestParam(required = false) Task.Status status,
            @RequestParam(required = false) UUID teamId,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(assigneeId, status, teamId, search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .priority(request.getPriority())
                .build();
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Task> assignTask(@PathVariable UUID id, @RequestParam UUID userId) {
        return ResponseEntity.ok(taskService.assignTask(id, userId));
    }

    @PatchMapping("/{id}/generate-description")
    public ResponseEntity<String> generateDescription(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(aiTaskService.generateDescription(task.getTitle()));
    }
}
