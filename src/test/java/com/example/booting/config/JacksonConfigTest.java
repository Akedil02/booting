package com.example.booting.config;

import com.example.booting.post.dto.PostCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JacksonConfigTest {

    @Test
    void shouldSerializePostCreatedEventWithLocalDateTime() throws Exception {
        ObjectMapper objectMapper = new JacksonConfig().objectMapper();

        PostCreatedEvent event = new PostCreatedEvent();
        event.setPostId("p1");
        event.setUserId("u1");
        event.setContent("hello");
        event.setHashtags(List.of("spring"));
        event.setTimestamp(LocalDateTime.of(2026, 4, 13, 0, 15, 16));

        String json = objectMapper.writeValueAsString(event);

        assertTrue(json.contains("\"timestamp\":\"2026-04-13T00:15:16\""));
    }
}
