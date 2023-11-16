package com.example.demo.User_Event;

import com.example.demo.Event.Event;
import com.example.demo.Event.EventRepository;
import com.example.demo.Event.EventService;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventService eventService;


    public void bindEventToUser(Long eventId, Long userId) {
        Event event = eventService.getById(eventId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.getEvents().add(event);
        userRepository.save(user);
    }

    }






