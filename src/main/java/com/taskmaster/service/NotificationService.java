package com.taskmaster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyUser(String username, String message) {
        messagingTemplate.convertAndSend("/topic/notifications/" + username, message);
    }
}
