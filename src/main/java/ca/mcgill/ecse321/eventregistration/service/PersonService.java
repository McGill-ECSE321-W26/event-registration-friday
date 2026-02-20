package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonResponseDto createPerson(PersonCreationRequestDto personCreationRequestDto) {
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
        return new PersonResponseDto(personRepository.findPersonById(id));
    }

    // public Person updatePersons

    // public void deletePerson

    // public Person getPersonById
}
