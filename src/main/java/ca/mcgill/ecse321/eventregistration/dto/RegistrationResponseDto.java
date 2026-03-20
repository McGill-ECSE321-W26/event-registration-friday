package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public class RegistrationResponseDto {
    private int personId;
    private int eventId;

    public RegistrationResponseDto() {}

    public RegistrationResponseDto(Registration registration) {
        this.personId = registration.getRegistrationId().getRegistrant().getId();
        this.eventId = registration.getRegistrationId().getEvent().getId();
    }

    public int getPersonId() { return personId; }
    public int getEventId() { return eventId; }
}
