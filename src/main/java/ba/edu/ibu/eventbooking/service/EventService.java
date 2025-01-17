package ba.edu.ibu.eventbooking.service;

import ba.edu.ibu.eventbooking.model.Event;
import ba.edu.ibu.eventbooking.repository.EventRepository;
import ba.edu.ibu.eventbooking.rest.dto.*;
import core.api.mailsender.exceptions.general.EventException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findAll();

        return events
                .stream()
                .map(EventDTO::new)
                .collect(toList());
    }

    public EventDTO getEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventException("The event with the given ID does not exist.");
        }
        return new EventDTO(event.get());
    }
    public EventDTO createEvent(EventRequestDTO payload) {
        Event event = eventRepository.save(payload.toEntity());
        return new EventDTO(event);
    }

    public EventDTO updateEvent(int id, EventRequestDTO payload) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventException("The event with the given ID does not exist.");
        }
        Event updatedEvent = payload.toEntity();
        updatedEvent.setEventId(event.get().getEventId());
        updatedEvent = eventRepository.save(updatedEvent);
        return new EventDTO(updatedEvent);
    }

    public void deleteEvent(int id) {
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(eventRepository::delete);
    }
    public List<Event> findAll() {
        return eventRepository.findAll();
    }


    public Event findById(int eventId) {
        Event event = eventRepository.findByEventId(eventId);

        if (event == null) {
            throw new EventException("Event not found with ID: " + eventId);
        }

        return event;
    }

}

