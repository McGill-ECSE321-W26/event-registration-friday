package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.sql.Time;

public class OnlineEventRequestDto {
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int registrationLimit;
    private String url;

    public OnlineEventRequestDto() {}

    public OnlineEventRequestDto(String name, Date date, Time startTime, Time endTime, int registrationLimit, String url) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationLimit = registrationLimit;
        this.url = url;
    }

    public String getName() { return name; }
    public Date getDate() { return date; }
    public Time getStartTime() { return startTime; }
    public Time getEndTime() { return endTime; }
    public int getRegistrationLimit() { return registrationLimit; }
    public String getUrl() { return url; }
}
