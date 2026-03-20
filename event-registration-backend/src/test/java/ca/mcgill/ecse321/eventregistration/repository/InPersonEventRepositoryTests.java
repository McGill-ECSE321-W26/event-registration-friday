package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InPersonEventRepositoryTests {
    @Autowired
    private InPersonEventRepository repository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        repository.deleteAll();
    }

    @Test
    public void testCreateAndReadInPersonEventAsEvent() {
        String name = "McGill Juggling Fest";
        Date date = new Date(2026, 2, 13);
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String address = "McGill";
        InPersonEvent juggling = new InPersonEvent(name, date, startTime, endTime, limit, address);

        juggling = repository.save(juggling);

        InPersonEvent jugglingFromDb = repository.findEventById(juggling.getId());

        assertNotNull(jugglingFromDb);
        assertEquals(name, jugglingFromDb.getName());
        assertEquals(date, jugglingFromDb.getDate());
        assertEquals(startTime, jugglingFromDb.getStartTime());
        assertEquals(endTime, jugglingFromDb.getEndTime());
        assertEquals(limit, jugglingFromDb.getRegistrationLimit());
        assertEquals(address, jugglingFromDb.getAddress());
    }
}
