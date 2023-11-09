package com.example.demo.Event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    public List<Event> getAll() { return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found" + id));    }

    public Event create(Event event) {
        return eventRepository.save(event);

    }

    public Event update(Long id, Event event) {
        Event eventToUpdate = getById(id);
        eventToUpdate.setType(event.getType());
        eventToUpdate.setName(event.getName());
        eventToUpdate.setStart(event.getStart());
        eventToUpdate.setEnd(event.getEnd());
        eventToUpdate.setDescription(event.getDescription());
        return eventRepository.save(eventToUpdate);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
