package com.taskmaster.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamRequest {
    private String name;
    private String description;
}
