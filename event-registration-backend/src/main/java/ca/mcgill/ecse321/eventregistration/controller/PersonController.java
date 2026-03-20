package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.service.PersonService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a new person in the system.
     * @param personCreationRequestDto the details of the person to create, including name, email, and password
     * @return a PersonResponseDto containing the details of the newly created person, including their ID and creation date
     */
    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "400", description = "Request body fails validation (blank fields, invalid email, or password too short)")
    })
    public PersonResponseDto createPerson(@RequestBody PersonCreationRequestDto personCreationRequestDto) {
        return personService.createPerson(personCreationRequestDto);
    }

    /**
     * Retrieves a person by their ID.
     * @param id the ID of the person to retrieve
     * @return a PersonResponseDto containing the details of the person with the specified ID
     */
    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No person found with the given ID")
    })
    public PersonResponseDto getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    /**
     * Deletes a person by their ID.
     * @param id the ID of the person to delete
     */
    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "No person found with the given ID")
    })
    public void deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
    }
}
