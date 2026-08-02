package com.taskmaster.dto;

import com.taskmaster.model.Task;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Task.Status status;
    private Task.Priority priority;
    private java.util.UUID teamId;
    private java.util.UUID assigneeId;
}
