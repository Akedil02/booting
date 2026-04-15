package com.example.booting.notification.service;

import com.example.booting.notification.entity.Notification;
import com.example.booting.notification.repository.NotificationRepository;
import com.example.booting.post.dto.PostCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumerService.class);

    private final ObjectMapper objectMapper;
    private final NotificationRepository notificationRepository;

    public NotificationConsumerService(ObjectMapper objectMapper, NotificationRepository notificationRepository) {
        this.objectMapper = objectMapper;
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "posts", groupId = "notification-group")
    public void consumePostCreatedEvent(String payload) {
        PostCreatedEvent event = deserialize(payload);

        String message = "new post " + event.getPostId();
        Notification notification = new Notification();
        notification.setUserId(event.getUserId());
        notification.setPostId(event.getPostId());
        notification.setMessage(message);
        notification.setCreatedAt(event.getTimestamp());
        notificationRepository.save(notification);

        log.info("🔔 Sending push notification to followers of user {} — new post {}",
                event.getUserId(),
                event.getPostId());
    }

    private PostCreatedEvent deserialize(String payload) {
        try {
            return objectMapper.readValue(payload, PostCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize PostCreatedEvent", e);
        }
    }
}
