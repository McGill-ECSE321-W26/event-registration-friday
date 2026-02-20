package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {
    private int id;
    private String name;
    private String email;

    public PersonResponseDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
