package com.example.booting.feed.service;

import com.example.booting.post.dto.PostCreatedEvent;
import com.example.booting.feed.entity.FeedItem;
import com.example.booting.feed.repository.FeedItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FeedConsumerService {

    private static final Logger log = LoggerFactory.getLogger(FeedConsumerService.class);

    private final ObjectMapper objectMapper;
    private final FeedItemRepository feedItemRepository;

    public FeedConsumerService(ObjectMapper objectMapper, FeedItemRepository feedItemRepository) {
        this.objectMapper = objectMapper;
        this.feedItemRepository = feedItemRepository;
    }

    @KafkaListener(topics = "posts", groupId = "feed-group")
    public void consumePostCreatedEvent(String payload) {
        PostCreatedEvent event = deserialize(payload);
        if (event.getContent() == null || event.getContent().isBlank()) {
            log.warn("⚠ Skipping event {} — content is empty", event.getPostId());
            return;
        }

        FeedItem feedItem = new FeedItem();
        feedItem.setPostId(event.getPostId());
        feedItem.setUserId(event.getUserId());
        feedItem.setContent(event.getContent());
        feedItem.setCreatedAt(event.getTimestamp());
        feedItemRepository.save(feedItem);

        log.info("📰 Adding post {} by user {} to follower feeds — '{}'",
                event.getPostId(),
                event.getUserId(),
                event.getContent());
    }

    private PostCreatedEvent deserialize(String payload) {
        try {
            return objectMapper.readValue(payload, PostCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize PostCreatedEvent", e);
        }
    }
}
