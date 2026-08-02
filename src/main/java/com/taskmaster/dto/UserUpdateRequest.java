package com.taskmaster.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
}
