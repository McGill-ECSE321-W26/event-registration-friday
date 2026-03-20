package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;

@Service
@Validated
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonResponseDto createPerson(@Valid PersonCreationRequestDto personCreationRequestDto) {
        Person person = new Person(
                personCreationRequestDto.getName(),
                personCreationRequestDto.getEmail(),
                personCreationRequestDto.getPassword(),
                new Date(System.currentTimeMillis())
        );
        return new PersonResponseDto(personRepository.save(person));
    }

    @Transactional
    public PersonResponseDto getPersonById(int id) {
        Person person = personRepository.findPersonById(id);
        if (person == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        return new PersonResponseDto(person);
    }

    @Transactional
    public void deletePerson(int id) {
        Person person = personRepository.findPersonById(id);
        if (person == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        personRepository.delete(person);
    }
}
