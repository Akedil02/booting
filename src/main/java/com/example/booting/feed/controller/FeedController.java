package com.example.booting.feed.controller;

import com.example.booting.feed.dto.FeedItemResponse;
import com.example.booting.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@Tag(name = "Feed", description = "Feed read APIs")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @Operation(summary = "Get feed items by user id")
    @ApiResponse(responseCode = "200", description = "Feed items returned successfully")
    @GetMapping
    public ResponseEntity<List<FeedItemResponse>> getFeedByUserId(
            @Parameter(description = "User id", example = "u1")
            @RequestParam String userId
    ) {
        return ResponseEntity.ok(feedService.getFeedByUserId(userId));
    }
}
