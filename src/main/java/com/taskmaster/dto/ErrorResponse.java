package com.taskmaster.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse() {}

    public ErrorResponse(int statusCode, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public static ErrorResponse builder() {
        return new ErrorResponse();
    }

    public ErrorResponse statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse timestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ErrorResponse build() {
        return this;
    }
}
