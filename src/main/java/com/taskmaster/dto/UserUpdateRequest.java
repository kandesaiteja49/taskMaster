package com.taskmaster.dto;

public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;

    public UserUpdateRequest() {}

    public UserUpdateRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public static UserUpdateRequest builder() {
        return new UserUpdateRequest();
    }

    public UserUpdateRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserUpdateRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserUpdateRequest email(String email) {
        this.email = email;
        return this;
    }

    public UserUpdateRequest build() {
        return this;
    }
}
