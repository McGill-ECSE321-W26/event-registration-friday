package ca.mcgill.ecse321.eventregistration.dto;

public class RegistrationRequestDto {
    private int personId;
    private int eventId;

    public RegistrationRequestDto() {}

    public RegistrationRequestDto(int personId, int eventId) {
        this.personId = personId;
        this.eventId = eventId;
    }

    public int getPersonId() { return personId; }
    public int getEventId() { return eventId; }
}
