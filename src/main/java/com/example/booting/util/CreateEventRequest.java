package com.example.booting.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateEventRequest", description = "Payload used to create a new event")
public class CreateEventRequest {

    @NotBlank(message = "Title must not be blank")
    @Schema(description = "Event title", example = "Spring Boot Workshop")
    private String title;

    @Email(message = "Organizer email must be valid")
    @Schema(description = "Organizer email address", example = "organizer@example.com")
    private String organizerEmail;

    @Min(value = 0, message = "Ticket price must be at least 0")
    @Schema(description = "Ticket price in local currency", example = "99.5", minimum = "0")
    private Double ticketPrice;

    @Schema(description = "Long event description", example = "Hands-on workshop covering controllers and JPA.")
    private String description;

    public CreateEventRequest() {
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
}
