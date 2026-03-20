package ca.mcgill.ecse321.eventregistration.integration;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonCreationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonIntegrationTest {
    private int createdPersonId;

    @Autowired
    private TestRestTemplate client;

    @Autowired
    private PersonRepository personRepository;

    @BeforeAll
    @AfterAll
    public void clearDatabase() {
        personRepository.deleteAll();;
    }

    @Test
    @Order(1)
    public void testCreateValidPerson() {
        PersonCreationRequestDto personCreationRequestDto =
                new PersonCreationRequestDto(
                        "Shuzhao Feng",
                        "shuzhao.feng@mail.mcgill.ca",
                        "12345678"
                );

        ResponseEntity<PersonResponseDto> response =
                client.postForEntity(
                        "/person",
                        personCreationRequestDto,
                        PersonResponseDto.class
                );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        PersonResponseDto responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Shuzhao Feng", responseBody.getName());
        assertEquals("shuzhao.feng@mail.mcgill.ca", responseBody.getEmail());

        createdPersonId = responseBody.getId();
    }

    @Test
    @Order(2)
    public void testCreateInvalidEmail() {
        PersonCreationRequestDto personCreationRequestDto =
                new PersonCreationRequestDto(
                        "Shuzhao Feng",
                        "shuzhao.fengmail.mcgill.ca",
                        "12345678"
                );

        ResponseEntity<ErrorDto> response =
                client.postForEntity(
                        "/person",
                        personCreationRequestDto,
                        ErrorDto.class
                );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("must be a well-formed email address; ", responseBody.getError());
    }

    @Test
    @Order(3)
    public void testFindPersonById() {
        ResponseEntity<PersonResponseDto> response =
                client.getForEntity(
                        "/person/" + createdPersonId,
                        PersonResponseDto.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        PersonResponseDto responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Shuzhao Feng", responseBody.getName());
        assertEquals("shuzhao.feng@mail.mcgill.ca", responseBody.getEmail());
    }

}
