package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.OnlineEventRequestDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import java.util.List;
import ca.mcgill.ecse321.eventregistration.repository.OnlineEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OnlineEventService {
    private final OnlineEventRepository onlineEventRepository;

    @Autowired
    public OnlineEventService(OnlineEventRepository onlineEventRepository) {
        this.onlineEventRepository = onlineEventRepository;
    }

    @Transactional
    public OnlineEvent createOnlineEvent(OnlineEventRequestDto dto) {
        OnlineEvent event = new OnlineEvent(dto.getName(), dto.getDate(), dto.getStartTime(), dto.getEndTime(), dto.getRegistrationLimit(), dto.getUrl());
        return onlineEventRepository.save(event);
    }

    @Transactional
    public OnlineEvent updateOnlineEvent(int id, OnlineEventRequestDto dto) {
        OnlineEvent event = onlineEventRepository.findEventById(id);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Online event not found.");
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setRegistrationLimit(dto.getRegistrationLimit());
        event.setUrl(dto.getUrl());
        return onlineEventRepository.save(event);
    }

    @Transactional
    public List<OnlineEvent> getAllOnlineEvents() {
        return (List<OnlineEvent>) onlineEventRepository.findAll();
    }

    @Transactional
    public void deleteOnlineEvent(int id) {
        OnlineEvent event = onlineEventRepository.findEventById(id);
        if (event == null) throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Online event not found.");
        onlineEventRepository.delete(event);
    }
}
