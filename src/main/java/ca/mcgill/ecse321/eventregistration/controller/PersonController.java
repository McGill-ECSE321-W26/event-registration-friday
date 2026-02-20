package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.service.PersonService;
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
    public PersonResponseDto createPerson(@RequestBody PersonCreationRequestDto personCreationRequestDto) {
        return personService.createPerson(personCreationRequestDto);
    }

    /**
     * Retrieves a person by their ID.
     * @param id the ID of the person to retrieve
     * @return a PersonResponseDto containing the details of the person with the specified ID
     */
    @GetMapping("/person/{id}")
    public PersonResponseDto getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }
}
