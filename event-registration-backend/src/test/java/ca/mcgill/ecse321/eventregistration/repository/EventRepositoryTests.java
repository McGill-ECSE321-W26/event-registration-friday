package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventRepositoryTests {
    @Autowired
    private EventRepository repository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        repository.deleteAll();
    }

    @Test
    public void testCreateAndReadInPersonEvent() {
        String name = "McGill Juggling Fest";
        Date date = new Date(2026, 2, 13);
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String address = "McGill";
        InPersonEvent juggling = new InPersonEvent(name, date, startTime, endTime, limit, address);

        juggling = repository.save(juggling);

        Event jugglingFromDb = repository.findEventById(juggling.getId());

        assertNotNull(jugglingFromDb);
        assertEquals(name, jugglingFromDb.getName());
        assertEquals(date, jugglingFromDb.getDate());
        assertEquals(startTime, jugglingFromDb.getStartTime());
        assertEquals(endTime, jugglingFromDb.getEndTime());
        assertEquals(limit, jugglingFromDb.getRegistrationLimit());
        assertInstanceOf(InPersonEvent.class, jugglingFromDb, "The event should be in-person.");
        assertEquals(address, ((InPersonEvent) jugglingFromDb).getAddress());
    }

    @Test
    public void testCreateAndReadOnlineEvent() {
        String name = "Zoom Meeting";
        Date date = new Date(2026, 2, 13);
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String url = "https://mcgill.zoom.us/j/01234567890";
        OnlineEvent zoomMeeting = new OnlineEvent(name, date, startTime, endTime, limit, url);

        zoomMeeting = repository.save(zoomMeeting);

        Event zoomMeetingFromDb = repository.findEventById(zoomMeeting.getId());

        assertNotNull(zoomMeetingFromDb);
        assertEquals(name, zoomMeetingFromDb.getName());
        assertEquals(date, zoomMeetingFromDb.getDate());
        assertEquals(startTime, zoomMeetingFromDb.getStartTime());
        assertEquals(endTime, zoomMeetingFromDb.getEndTime());
        assertEquals(limit, zoomMeetingFromDb.getRegistrationLimit());
        assertInstanceOf(OnlineEvent.class, zoomMeetingFromDb, "The event should be online.");
        assertEquals(url, ((OnlineEvent) zoomMeetingFromDb).getUrl());
    }
}
