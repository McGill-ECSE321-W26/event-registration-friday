package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.repository.EventRepository;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, PersonRepository personRepository, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.personRepository = personRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public RegistrationResponseDto getRegistration(int personId, int eventId) {
        Person person = personRepository.findPersonById(personId);
        if (person == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        Event event = eventRepository.findEventById(eventId);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");
        Registration registration = registrationRepository.findRegistrationByRid(new Registration.RegistrationId(person, event));
        if (registration == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Registration not found.");
        return new RegistrationResponseDto(registration);
    }

    @Transactional
    public RegistrationResponseDto createRegistration(int personId, int eventId) {
        Person person = personRepository.findPersonById(personId);
        if (person == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        Event event = eventRepository.findEventById(eventId);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");
        Registration registration = new Registration(new Registration.RegistrationId(person, event));
        return new RegistrationResponseDto(registrationRepository.save(registration));
    }

    @Transactional
    public RegistrationResponseDto updateRegistration(int personId, int eventId, int newPersonId, int newEventId) {
        Person oldPerson = personRepository.findPersonById(personId);
        if (oldPerson == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        Event oldEvent = eventRepository.findEventById(eventId);
        if (oldEvent == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");

        Person newPerson = personRepository.findPersonById(newPersonId);
        if (newPerson == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "New person not found.");
        Event newEvent = eventRepository.findEventById(newEventId);
        if (newEvent == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "New event not found.");

        registrationRepository.deleteById(new Registration.RegistrationId(oldPerson, oldEvent));
        return new RegistrationResponseDto(registrationRepository.save(new Registration(new Registration.RegistrationId(newPerson, newEvent))));
    }

    @Transactional
    public void deleteRegistration(int personId, int eventId) {
        Person person = personRepository.findPersonById(personId);
        if (person == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
        Event event = eventRepository.findEventById(eventId);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");
        registrationRepository.deleteById(new Registration.RegistrationId(person, event));
    }
}
