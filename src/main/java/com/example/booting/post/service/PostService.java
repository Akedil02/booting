package com.example.booting.post.service;

import com.example.booting.post.dto.PostCreatedEvent;
import com.example.booting.post.dto.CreatePostRequest;
import com.example.booting.post.dto.PostResponse;

public interface PostService {
    PostCreatedEvent publishPost(CreatePostRequest request);
    PostResponse getPostById(String postId);
}
