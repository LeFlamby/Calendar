package com.example.demo.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository repository;
    public List<Calendar> getAll() {
        return repository.findAll();

    }

    public Calendar getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found" + id));
    }

    public Calendar create(Calendar calendar) {
        return repository.save(calendar);
    }

    public Calendar update(Long id, Calendar calendar) {
        Calendar calendarToUpdate = getById(id);
        calendarToUpdate.setCode(calendar.getCode());
        //calendarToUpdate.setOwner(calendar.getOwner());
        return repository.save(calendarToUpdate);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
