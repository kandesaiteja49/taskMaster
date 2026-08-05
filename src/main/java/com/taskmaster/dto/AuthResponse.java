package com.taskmaster.dto;

public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse() {}

    public AuthResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public static AuthResponse builder() {
        return new AuthResponse();
    }

    public AuthResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public AuthResponse tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public AuthResponse build() {
        return this;
    }
}
