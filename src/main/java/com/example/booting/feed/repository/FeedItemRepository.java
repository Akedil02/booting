package com.example.booting.feed.repository;

import com.example.booting.feed.entity.FeedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedItemRepository extends JpaRepository<FeedItem, Long> {
    List<FeedItem> findByUserIdOrderByCreatedAtDesc(String userId);
}
