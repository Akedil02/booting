package com.example.booting.post.service;

import com.example.booting.post.dto.PostCreatedEvent;
import com.example.booting.post.dto.PostResponse;
import com.example.booting.post.dto.CreatePostRequest;
import com.example.booting.post.entity.Post;
import com.example.booting.exception.ResourceNotFoundException;
import com.example.booting.post.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private static final String POSTS_TOPIC = "posts";
    private static final String PUBLISHED_STATUS = "PUBLISHED";

    private final PostRepository postRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public PostServiceImpl(
            PostRepository postRepository,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper
    ) {
        this.postRepository = postRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public PostCreatedEvent publishPost(CreatePostRequest request) {
        String postId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Post post = new Post();
        post.setPostId(postId);
        post.setUserId(request.userId());
        post.setContent(request.content());
        post.setHashtagsJson(serializeHashtags(request));
        post.setStatus(PUBLISHED_STATUS);
        post.setCreatedAt(now);

        postRepository.save(post);

        PostCreatedEvent event = new PostCreatedEvent();
        event.setPostId(postId);
        event.setUserId(request.userId());
        event.setContent(request.content());
        event.setHashtags(request.hashtags());
        event.setTimestamp(now);

        kafkaTemplate.send(POSTS_TOPIC, postId, serializeEvent(event));

        return event;
    }

    @Override
    public PostResponse getPostById(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        return toPostResponse(post);
    }

    private String serializeHashtags(CreatePostRequest request) {
        try {
            return objectMapper.writeValueAsString(request.hashtags());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize hashtags", e);
        }
    }

    private String serializeEvent(PostCreatedEvent event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize PostCreatedEvent", e);
        }
    }

    private PostResponse toPostResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setPostId(post.getPostId());
        response.setUserId(post.getUserId());
        response.setContent(post.getContent());
        response.setHashtags(deserializeHashtags(post.getHashtagsJson()));
        response.setStatus(post.getStatus());
        response.setTimestamp(post.getCreatedAt());
        return response;
    }

    private List<String> deserializeHashtags(String hashtagsJson) {
        try {
            return objectMapper.readValue(hashtagsJson, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to deserialize hashtags", e);
        }
    }
}
