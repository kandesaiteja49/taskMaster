package com.taskmaster.service;

import com.taskmaster.exception.ResourceNotFoundException;
import com.taskmaster.model.*;
import com.taskmaster.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final NotificationService notificationService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TeamRepository teamRepository, NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Task createTask(Task task, User creator, Team team) {
        task.setCreator(creator);
        task.setTeam(team);
        return taskRepository.save(task);
    }

    public Page<Task> getAllTasks(UUID assigneeId, Task.Status status, UUID teamId, String search, Pageable pageable) {
        Specification<Task> spec = Specification.where(null);

        if (assigneeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("assignee").get("id"), assigneeId));
        }
        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (teamId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("team").get("id"), teamId));
        }
        if (search != null && !search.isBlank()) {
            spec = spec.and((root, query, cb) ->
                cb.or(
                    cb.like(cb.lower(root.get("title").as(String.class)), "%" + search.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("description").as(String.class)), "%" + search.toLowerCase() + "%")
                )
            );
        }

        return taskRepository.findAll(spec, pageable);
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Transactional
    public Task updateTask(UUID id, Task taskDetails) {
        Task task = getTaskById(id);

        if (taskDetails.getTitle() != null) task.setTitle(taskDetails.getTitle());
        if (taskDetails.getDescription() != null) task.setDescription(taskDetails.getDescription());
        if (taskDetails.getDueDate() != null) task.setDueDate(taskDetails.getDueDate());
        if (taskDetails.getStatus() != null) task.setStatus(taskDetails.getStatus());
        if (taskDetails.getPriority() != null) task.setPriority(taskDetails.getPriority());

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(UUID id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Transactional
    public Task assignTask(UUID taskId, UUID userId) {
        Task task = getTaskById(taskId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        task.setAssignee(user);
        Task savedTask = taskRepository.save(task);

        notificationService.notifyUser(user.getUsername(), "You have been assigned to task: " + task.getTitle());

        return savedTask;
    }
}
