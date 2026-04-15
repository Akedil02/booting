package com.example.booting.event.service;

import com.example.booting.event.dto.CreateEventRequest;
import com.example.booting.event.dto.EventResponse;
import com.example.booting.event.entity.Event;
import com.example.booting.exception.ResourceNotFoundException;
import com.example.booting.event.mapper.EventMapper;
import com.example.booting.event.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventResponse createEvent(CreateEventRequest request) {
        log.info("Creating event with title: {}", request.getTitle());

        Event event = eventMapper.toEntity(request);
        event.setCreatedAt(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);

        log.debug("Saved event with id: {}", savedEvent.getId());

        return eventMapper.toResponse(savedEvent);
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        log.info("Fetching all events");
        return eventRepository.findAll(pageable).map(eventMapper::toResponse);
    }

    @Override
    public EventResponse getEventById(Long id) {
        log.info("Fetching event with id: {}", id);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        return eventMapper.toResponse(event);
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleting event with id: {}", id);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        eventRepository.delete(event);

        log.info("Deleted event with id: {}", id);
    }
}
