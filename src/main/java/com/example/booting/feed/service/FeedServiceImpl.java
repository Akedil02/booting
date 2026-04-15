package com.example.booting.feed.service;

import com.example.booting.feed.dto.FeedItemResponse;
import com.example.booting.feed.entity.FeedItem;
import com.example.booting.feed.repository.FeedItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedItemRepository feedItemRepository;

    public FeedServiceImpl(FeedItemRepository feedItemRepository) {
        this.feedItemRepository = feedItemRepository;
    }

    @Override
    public List<FeedItemResponse> getFeedByUserId(String userId) {
        return feedItemRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private FeedItemResponse toResponse(FeedItem item) {
        FeedItemResponse response = new FeedItemResponse();
        response.setUserId(item.getUserId());
        response.setPostId(item.getPostId());
        response.setContent(item.getContent());
        response.setCreatedAt(item.getCreatedAt());
        return response;
    }
}
