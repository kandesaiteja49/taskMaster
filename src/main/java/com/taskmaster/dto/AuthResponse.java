package com.taskmaster.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
