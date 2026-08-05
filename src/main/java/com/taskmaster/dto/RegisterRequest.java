package com.taskmaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;

    public RegisterRequest() {}

    public RegisterRequest(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public static RegisterRequest builder() {
        return new RegisterRequest();
    }

    public RegisterRequest username(String username) {
        this.username = username;
        return this;
    }

    public RegisterRequest email(String email) {
        this.email = email;
        return this;
    }

    public RegisterRequest password(String password) {
        this.password = password;
        return this;
    }

    public RegisterRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterRequest build() {
        return this;
    }
}
