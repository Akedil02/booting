package com.example.booting.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "EventResponse", description = "Event data returned by APIs")
public class EventResponse {

    @Schema(description = "Event id", example = "1")
    private Long id;

    @Schema(description = "Event title", example = "Spring Boot Workshop")
    private String title;

    @Schema(description = "Organizer email address", example = "organizer@example.com")
    private String organizerEmail;

    @Schema(description = "Ticket price in local currency", example = "99.5")
    private Double ticketPrice;

    @Schema(description = "Long event description", example = "Hands-on workshop covering controllers and JPA.")
    private String description;

    @Schema(description = "Event creation timestamp", example = "2026-04-01T10:15:30")
    private LocalDateTime createdAt;

    public EventResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
