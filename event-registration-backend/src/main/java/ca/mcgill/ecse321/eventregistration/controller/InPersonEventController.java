package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.InPersonEventRequestDto;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.service.InPersonEventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class InPersonEventController {
    private final InPersonEventService inPersonEventService;

    @Autowired
    public InPersonEventController(InPersonEventService inPersonEventService) {
        this.inPersonEventService = inPersonEventService;
    }

    /**
     * Retrieves all in-person events.
     * @return a list of all in-person events
     */
    @GetMapping("/event/inperson")
    @ResponseStatus(HttpStatus.OK)
    public List<InPersonEvent> getAllInPersonEvents() {
        return inPersonEventService.getAllInPersonEvents();
    }

    /**
     * Creates a new in-person event.
     * @param dto the details of the event to create, including name, date, times, registration limit, and address
     * @return the newly created in-person event
     */
    @PostMapping("/event/inperson")
    @ResponseStatus(HttpStatus.CREATED)
    public InPersonEvent createInPersonEvent(@RequestBody InPersonEventRequestDto dto) {
        return inPersonEventService.createInPersonEvent(dto);
    }

    /**
     * Updates an existing in-person event.
     * @param id the ID of the event to update
     * @param dto the updated event details
     * @return the updated in-person event
     */
    @PutMapping("/event/inperson/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No in-person event found with the given ID")
    })
    public InPersonEvent updateInPersonEvent(@PathVariable int id, @RequestBody InPersonEventRequestDto dto) {
        return inPersonEventService.updateInPersonEvent(id, dto);
    }

    /**
     * Deletes an in-person event by its ID.
     * @param id the ID of the event to delete
     */
    @DeleteMapping("/event/inperson/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No in-person event found with the given ID")
    })
    public void deleteInPersonEvent(@PathVariable int id) {
        inPersonEventService.deleteInPersonEvent(id);
    }
}
