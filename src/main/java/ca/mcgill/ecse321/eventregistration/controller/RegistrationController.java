package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.service.RegistrationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Retrieves a specific registration by person ID and event ID.
     * @param personId the person ID of the registration
     * @param eventId the event ID of the registration
     * @return the registration for the given person and event
     */
    @GetMapping("/registration/{personId}/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "Person, event, or registration not found with the given IDs")
    })
    public RegistrationResponseDto getRegistration(@PathVariable int personId, @PathVariable int eventId) {
        return registrationService.getRegistration(personId, eventId);
    }

    /**
     * Registers a person for an event.
     * @param dto the person ID and event ID to register
     * @return the created registration
     */
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "Person or event not found with the given IDs")
    })
    public RegistrationResponseDto createRegistration(@RequestBody RegistrationRequestDto dto) {
        return registrationService.createRegistration(dto.getPersonId(), dto.getEventId());
    }

    /**
     * Updates an existing registration by replacing its person and event with new ones.
     * @param personId the person ID of the registration to update
     * @param eventId the event ID of the registration to update
     * @param dto the new person ID and event ID
     * @return the updated registration
     */
    @PutMapping("/registration/{personId}/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "Existing or new person or event not found with the given IDs")
    })
    public RegistrationResponseDto updateRegistration(
            @PathVariable int personId, @PathVariable int eventId,
            @RequestBody RegistrationRequestDto dto) {
        return registrationService.updateRegistration(personId, eventId, dto.getPersonId(), dto.getEventId());
    }

    /**
     * Deletes a registration for a given person and event.
     * @param personId the person ID of the registration to delete
     * @param eventId the event ID of the registration to delete
     */
    @DeleteMapping("/registration/{personId}/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "Person or event not found with the given IDs")
    })
    public void deleteRegistration(@PathVariable int personId, @PathVariable int eventId) {
        registrationService.deleteRegistration(personId, eventId);
    }
}
