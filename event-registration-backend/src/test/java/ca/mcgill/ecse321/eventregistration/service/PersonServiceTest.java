package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PersonServiceTest {
    private static final String VALID_NAME = "Shuzhao Feng";
    private static final String VALID_EMAIL = "shuzhao.feng@mail.mcgill.ca";
    private static final String VALID_PASSWORD = "ShuzhaoF123456#";
    private static final Date VALID_CREATION_DATE = new Date(System.currentTimeMillis());

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testCreatePerson() {
        // Arrange
        PersonCreationRequestDto personCreationRequestDto = new PersonCreationRequestDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);
        Person fakePerson = new Person(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_CREATION_DATE);
        fakePerson.setId(1);
        when(personRepository.save(any(Person.class)))
                .thenReturn(fakePerson);

        // Act
        PersonResponseDto createdPerson = personService.createPerson(personCreationRequestDto);

        // Assert
        assertNotNull(createdPerson);
        assertEquals(VALID_NAME, createdPerson.getName());
        assertEquals(VALID_EMAIL, createdPerson.getEmail());
    }

    @Test
    public void testGetPersonById() {
        // Arrange
        Person fakePerson = new Person(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_CREATION_DATE);
        fakePerson.setId(1);
        when(personRepository.findPersonById(1)).thenReturn(fakePerson);

        // Act
        PersonResponseDto foundPerson = personService.getPersonById(1);

        // Assert
        assertNotNull(foundPerson);
        assertEquals(VALID_NAME, foundPerson.getName());
        assertEquals(VALID_EMAIL, foundPerson.getEmail());
    }
}
