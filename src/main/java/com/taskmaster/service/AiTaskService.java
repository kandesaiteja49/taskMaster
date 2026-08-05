package com.taskmaster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AiTaskService {

    private static final Logger log = LoggerFactory.getLogger(AiTaskService.class);

    public String generateDescription(String title) {
        log.info("Generating AI description for task: {}", title);

        return "This is an AI-generated description for the task '" + title + "'. " +
               "It includes key objectives, expected outcomes, and a suggested step-by-step approach " +
               "to ensure a high-quality delivery.";
    }
}
