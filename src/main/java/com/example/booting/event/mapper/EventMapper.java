package com.example.booting.event.mapper;

import com.example.booting.event.dto.CreateEventRequest;
import com.example.booting.event.dto.EventResponse;
import com.example.booting.event.entity.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {

    public Event toEntity(CreateEventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setOrganizerEmail(request.getOrganizerEmail());
        event.setTicketPrice(request.getTicketPrice());
        event.setDescription(request.getDescription());
        return event;
    }

    public EventResponse toResponse(Event event) {
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setOrganizerEmail(event.getOrganizerEmail());
        response.setTicketPrice(event.getTicketPrice());
        response.setDescription(event.getDescription());
        response.setCreatedAt(event.getCreatedAt());
        return response;
    }

    public List<EventResponse> toResponseList(List<Event> events) {
        List<EventResponse> responses = new ArrayList<>();
        for (Event event : events) {
            responses.add(toResponse(event));
        }
        return responses;
    }
}