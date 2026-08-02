package com.taskmaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
