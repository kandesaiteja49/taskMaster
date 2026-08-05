package com.taskmaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public AuthRequest() {}

    public AuthRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static AuthRequest builder() {
        return new AuthRequest();
    }

    public AuthRequest username(String username) {
        this.username = username;
        return this;
    }

    public AuthRequest email(String email) {
        this.email = email;
        return this;
    }

    public AuthRequest password(String password) {
        this.password = password;
        return this;
    }

    public AuthRequest build() {
        return this;
    }
}
