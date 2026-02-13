package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegistrationRepositoryTests {

    private final Date TODAY = new Date(2026, 2, 13);;

    private final Person MIKE = new Person(
            "Mike",
            "mike@mail.mcgill.ca",
            "mike123",
            TODAY);

    private final InPersonEvent CONCERT = new InPersonEvent(
            "Concert",
            TODAY,
            Time.valueOf("18:00:00"),
            Time.valueOf("21:00:00"),
            100,
            "1234 Main St, Montreal, QC");

    private final InPersonEvent CONCERT2 = new InPersonEvent(
            "Concert2",
            TODAY,
            Time.valueOf("19:00:00"),
            Time.valueOf("22:00:00"),
            200,
            "4321 Main St, Montreal, QC");

    @Autowired
    private RegistrationRepository regRepo;
    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private EventRepository eventRepo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        regRepo.deleteAll();
        personRepo.deleteAll();
        eventRepo.deleteAll();
    }

    @Test
    public void testCreateAndReadRegistration() {
        // Arrange
        Person mikeEntity = personRepo.save(MIKE);

        Event concertEntity = eventRepo.save(CONCERT);

        Registration registration = new Registration(new Registration.RegistrationId(mikeEntity, concertEntity));
        regRepo.save(registration);

        // Act
        Registration registrationFromDb = regRepo.findRegistrationByRid(registration.getRegistrationId());

        // Assert
        assertNotNull(registrationFromDb);
        assertNotNull(registrationFromDb.getRegistrationId());
        assertNotNull(registrationFromDb.getRegistrationId().getRegistrant());
        assertEquals(registrationFromDb.getRegistrationId().getRegistrant().getId(), MIKE.getId());
        assertNotNull(registrationFromDb.getRegistrationId().getEvent());
        assertEquals(registrationFromDb.getRegistrationId().getEvent().getId(), CONCERT.getId());
    }

    @Test
    public void testFindRegistrationsByRegistrantId() {
        // Arrange
        Person mikeEntity = personRepo.save(MIKE);
        Event concertEntity = eventRepo.save(CONCERT);
        Event concert2Entity = eventRepo.save(CONCERT2);

        Registration registration = new Registration(new Registration.RegistrationId(mikeEntity, concertEntity));
        regRepo.save(registration);

        Registration registration2 = new Registration(new Registration.RegistrationId(mikeEntity, concert2Entity));
        regRepo.save(registration2);

        // Act
        List<Registration> registrationsFromDb = regRepo.findRegistrationsByRidRegistrantIdOrderByRidEventId(MIKE.getId());

        // Assert
        int count = 0;
        for (Registration r : registrationsFromDb) {
            assertNotNull(r);
            assertNotNull(r.getRegistrationId());
            assertNotNull(r.getRegistrationId().getRegistrant());
            assertEquals(r.getRegistrationId().getRegistrant().getId(), MIKE.getId());
            assertNotNull(r.getRegistrationId().getEvent());
            count++;
        }
        assertEquals(2, count);
        assertEquals(CONCERT.getId(), registrationsFromDb.get(0).getRegistrationId().getEvent().getId());
        assertEquals(CONCERT2.getId(), registrationsFromDb.get(1).getRegistrationId().getEvent().getId());
    }
}
