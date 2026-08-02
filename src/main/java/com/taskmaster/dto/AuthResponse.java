package com.taskmaster.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// This class represents the response returned after a successful authentication, containing the access token and its type.
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
