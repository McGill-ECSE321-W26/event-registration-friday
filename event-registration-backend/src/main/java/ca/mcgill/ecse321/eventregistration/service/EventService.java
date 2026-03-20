package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.repository.EventRepository;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Transactional
    public Event getEventById(int id) {
        Event event = eventRepository.findEventById(id);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");
        return event;
    }
}
