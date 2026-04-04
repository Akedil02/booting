package com.example.booting.controller;

import com.example.booting.util.CreateEventRequest;
import com.example.booting.util.EventResponse;
import com.example.booting.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Event management APIs")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(
            summary = "Create event",
            description = "Creates a new event and returns the created event data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody CreateEventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(request));
    }

    @Operation(
            summary = "List events",
            description = "Returns all events."
    )
    @ApiResponse(responseCode = "200", description = "Events returned")
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @Operation(
            summary = "Get event by id",
            description = "Returns one event by its id."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event returned"),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(
            @Parameter(description = "Event id", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Operation(
            summary = "Delete event",
            description = "Deletes one event by id."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted"),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @Parameter(description = "Event id", example = "1")
            @PathVariable Long id
    ) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
