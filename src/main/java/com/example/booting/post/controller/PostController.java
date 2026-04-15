package com.example.booting.post.controller;

import com.example.booting.post.dto.PostCreatedEvent;
import com.example.booting.post.dto.PostResponse;
import com.example.booting.post.dto.CreatePostRequest;
import com.example.booting.exception.ApiErrorResponse;
import com.example.booting.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Publish a new post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post published successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<PostCreatedEvent> publishPost(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    required = true,
                    description = "Post creation payload",
                    content = @Content(schema = @Schema(implementation = CreatePostRequest.class))
            )
            CreatePostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.publishPost(request));
    }

    @Operation(summary = "Get post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post returned successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Post not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(
            @Parameter(description = "Post id", example = "abc-123")
            @PathVariable String postId
    ) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }
}
