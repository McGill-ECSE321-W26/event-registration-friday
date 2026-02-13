package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
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
public class OnlineEventRepositoryTests {
    @Autowired
    private OnlineEventRepository repository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        repository.deleteAll();
    }

    @Test
    public void testCreateAndReadOnlineEventAsEvent() {
        String name = "Zoom Meeting";
        Date date = new Date(2026, 2, 13);
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String url = "https://mcgill.zoom.us/j/01234567890";
        OnlineEvent zoomMeeting = new OnlineEvent(name, date, startTime, endTime, limit, url);

        zoomMeeting = repository.save(zoomMeeting);

        OnlineEvent zoomMeetingFromDb = repository.findEventById(zoomMeeting.getId());

        assertNotNull(zoomMeetingFromDb);
        assertEquals(name, zoomMeetingFromDb.getName());
        assertEquals(date, zoomMeetingFromDb.getDate());
        assertEquals(startTime, zoomMeetingFromDb.getStartTime());
        assertEquals(endTime, zoomMeetingFromDb.getEndTime());
        assertEquals(limit, zoomMeetingFromDb.getRegistrationLimit());
        assertEquals(url, zoomMeetingFromDb.getUrl());
    }
}
