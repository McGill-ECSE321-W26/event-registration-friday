package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.service.EventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves all events in the system, including both in-person and online events.
     * @return a list of all events
     */
    @GetMapping("/event")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    /**
     * Retrieves a specific event by its ID.
     * @param id the ID of the event to retrieve
     * @return the event with the specified ID
     */
    @GetMapping("/event/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No event found with the given ID")
    })
    public Event getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }
}
