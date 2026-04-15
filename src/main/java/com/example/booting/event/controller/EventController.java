package com.example.booting.event.controller;

import com.example.booting.event.dto.CreateEventRequest;
import com.example.booting.event.dto.EventResponse;
import com.example.booting.exception.ApiErrorResponse;
import com.example.booting.exception.ValidationErrorResponse;
import com.example.booting.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    required = true,
                    description = "Event creation payload",
                    content = @Content(schema = @Schema(implementation = CreateEventRequest.class))
            )
            CreateEventRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(request));
    }

    @Operation(
            summary = "List events",
            description = "Returns events using pagination."
    )
    @ApiResponse(responseCode = "200", description = "Events returned")
    @GetMapping
    public ResponseEntity<Page<EventResponse>> getAllEvents(
            @Parameter(description = "Pagination and sorting options")
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(eventService.getAllEvents(pageable));
    }

    @Operation(
            summary = "Get event by id",
            description = "Returns one event by its id."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event returned"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
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
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
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
