package com.taskmaster.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static LoginRequest builder() {
        return new LoginRequest();
    }

    public LoginRequest username(String username) {
        this.username = username;
        return this;
    }

    public LoginRequest password(String password) {
        this.password = password;
        return this;
    }

    public LoginRequest build() {
        return this;
    }
}
