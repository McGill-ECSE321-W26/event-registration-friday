package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.InPersonEventRequestDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import java.util.List;
import ca.mcgill.ecse321.eventregistration.repository.InPersonEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class InPersonEventService {
    private final InPersonEventRepository inPersonEventRepository;

    @Autowired
    public InPersonEventService(InPersonEventRepository inPersonEventRepository) {
        this.inPersonEventRepository = inPersonEventRepository;
    }

    @Transactional
    public InPersonEvent createInPersonEvent(InPersonEventRequestDto dto) {
        InPersonEvent event = new InPersonEvent(dto.getName(), dto.getDate(), dto.getStartTime(), dto.getEndTime(), dto.getRegistrationLimit(), dto.getAddress());
        return inPersonEventRepository.save(event);
    }

    @Transactional
    public InPersonEvent updateInPersonEvent(int id, InPersonEventRequestDto dto) {
        InPersonEvent event = inPersonEventRepository.findEventById(id);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "In-person event not found.");
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setRegistrationLimit(dto.getRegistrationLimit());
        event.setAddress(dto.getAddress());
        return inPersonEventRepository.save(event);
    }

    @Transactional
    public List<InPersonEvent> getAllInPersonEvents() {
        return (List<InPersonEvent>) inPersonEventRepository.findAll();
    }

    @Transactional
    public void deleteInPersonEvent(int id) {
        InPersonEvent event = inPersonEventRepository.findEventById(id);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "In-person event not found.");
        inPersonEventRepository.delete(event);
    }
}
