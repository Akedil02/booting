package com.example.booting.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreatePostRequest(
        @NotBlank
        @Size(max = 50)
        @Schema(description = "ID of the user creating the post", example = "user-42")
        String userId,

        @NotBlank
        @Size(max = 280)
        @Schema(description = "Post content, max 280 characters", example = "Hello Kafka!")
        String content,

        @NotNull
        @NotEmpty
        @Schema(description = "List of hashtags without #", example = "[\"kafka\", \"spring\"]")
        List<String> hashtags
) {
}
