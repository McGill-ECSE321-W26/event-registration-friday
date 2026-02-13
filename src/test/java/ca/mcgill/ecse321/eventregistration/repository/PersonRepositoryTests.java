package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonRepositoryTests {
    @Autowired
    private PersonRepository repo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        repo.deleteAll();
    }

    @Test
    public void testCreateAndReadPerson() {
        // Arrange
        Date today = new Date(2026, 2, 13);
        Person mike = new Person(
                "Mike",
                "mike@mail.mcgill.ca",
                "mike123",
                today);
        mike = repo.save(mike);

        // Act
        Person peteMikeJoeFromDb = repo.findPersonById(mike.getId());

        // Assert
        assertNotNull(peteMikeJoeFromDb);
        assertEquals(mike.getId(), peteMikeJoeFromDb.getId());
        assertEquals(mike.getName(), peteMikeJoeFromDb.getName());
        assertEquals(mike.getEmail(), peteMikeJoeFromDb.getEmail());
        assertEquals(mike.getPassword(), peteMikeJoeFromDb.getPassword());
        assertEquals(mike.getCreationDate(), peteMikeJoeFromDb.getCreationDate());
    }

}
