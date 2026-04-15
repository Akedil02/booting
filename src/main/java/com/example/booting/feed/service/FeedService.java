package com.example.booting.feed.service;

import com.example.booting.feed.dto.FeedItemResponse;

import java.util.List;

public interface FeedService {
    List<FeedItemResponse> getFeedByUserId(String userId);
}
