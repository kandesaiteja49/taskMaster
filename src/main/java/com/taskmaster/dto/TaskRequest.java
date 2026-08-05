package com.taskmaster.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.taskmaster.model.Task;

public class TaskRequest {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Task.Status status;
    private Task.Priority priority;
    private UUID teamId;
    private UUID assigneeId;

    public TaskRequest() {}

    public TaskRequest(String title, String description, LocalDate dueDate, Task.Status status, Task.Priority priority, UUID teamId, UUID assigneeId) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.teamId = teamId;
        this.assigneeId = assigneeId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Task.Status getStatus() { return status; }
    public void setStatus(Task.Status status) { this.status = status; }

    public Task.Priority getPriority() { return priority; }
    public void setPriority(Task.Priority priority) { this.priority = priority; }

    public UUID getTeamId() { return teamId; }
    public void setTeamId(UUID teamId) { this.teamId = teamId; }

    public UUID getAssigneeId() { return assigneeId; }
    public void setAssigneeId(UUID assigneeId) { this.assigneeId = assigneeId; }

    public static TaskRequest builder() {
        return new TaskRequest();
    }

    public TaskRequest title(String title) {
        this.title = title;
        return this;
    }

    public TaskRequest description(String description) {
        this.description = description;
        return this;
    }

    public TaskRequest dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskRequest status(Task.Status status) {
        this.status = status;
        return this;
    }

    public TaskRequest priority(Task.Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskRequest teamId(UUID teamId) {
        this.teamId = teamId;
        return this;
    }

    public TaskRequest assigneeId(UUID assigneeId) {
        this.assigneeId = assigneeId;
        return this;
    }

    public TaskRequest build() {
        return this;
    }
}
