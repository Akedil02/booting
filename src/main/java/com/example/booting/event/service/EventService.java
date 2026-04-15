package com.example.booting.event.service;

import com.example.booting.event.dto.CreateEventRequest;
import com.example.booting.event.dto.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventResponse createEvent(CreateEventRequest request);

    Page<EventResponse> getAllEvents(Pageable pageable);

    EventResponse getEventById(Long id);

    void deleteEvent(Long id);
}
