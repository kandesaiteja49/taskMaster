package com.taskmaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
public class HealthCheckController {

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Online");
        response.put("message", "The TaskMaster Backend is running successfully!");
        response.put("instructions", "Please use Postman for API requests or /h2-console for database view.");
        return response;
    }
}
