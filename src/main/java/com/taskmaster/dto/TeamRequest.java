package com.taskmaster.dto;

public class TeamRequest {
    private String name;
    private String description;

    public TeamRequest() {}

    public TeamRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public static TeamRequest builder() {
        return new TeamRequest();
    }

    public TeamRequest name(String name) {
        this.name = name;
        return this;
    }

    public TeamRequest description(String description) {
        this.description = description;
        return this;
    }

    public TeamRequest build() {
        return this;
    }
}
