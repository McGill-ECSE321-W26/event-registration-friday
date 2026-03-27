package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.OnlineEventRequestDto;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import ca.mcgill.ecse321.eventregistration.service.OnlineEventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class OnlineEventController {
    private final OnlineEventService onlineEventService;

    @Autowired
    public OnlineEventController(OnlineEventService onlineEventService) {
        this.onlineEventService = onlineEventService;
    }

    /**
     * Retrieves all online events.
     * @return a list of all online events
     */
    @GetMapping("/event/online")
    @ResponseStatus(HttpStatus.OK)
    public List<OnlineEvent> getAllOnlineEvents() {
        return onlineEventService.getAllOnlineEvents();
    }

    /**
     * Creates a new online event.
     * @param dto the details of the event to create, including name, date, times, registration limit, and URL
     * @return the newly created online event
     */
    @PostMapping("/event/online")
    @ResponseStatus(HttpStatus.CREATED)
    public OnlineEvent createOnlineEvent(@RequestBody OnlineEventRequestDto dto) {
        return onlineEventService.createOnlineEvent(dto);
    }

    /**
     * Updates an existing online event.
     * @param id the ID of the event to update
     * @param dto the updated event details
     * @return the updated online event
     */
    @PutMapping("/event/online/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No online event found with the given ID")
    })
    public OnlineEvent updateOnlineEvent(@PathVariable int id, @RequestBody OnlineEventRequestDto dto) {
        return onlineEventService.updateOnlineEvent(id, dto);
    }

    /**
     * Deletes an online event by its ID.
     * @param id the ID of the event to delete
     */
    @DeleteMapping("/event/online/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No online event found with the given ID")
    })
    public void deleteOnlineEvent(@PathVariable int id) {
        onlineEventService.deleteOnlineEvent(id);
    }
}
