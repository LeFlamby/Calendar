package com.example.demo.general;

import com.example.demo.Event.Event;
import com.example.demo.Event.EventRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class GeneralService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;
    public void setUp() {

        Event conges = new Event();
        conges.setName("Congés");
        conges.setStart(new Date(2023 - 11 - 8));
        conges.setEnd(new Date(2023 - 11 - 15));
        conges.setDescription("Congés de Noël");
        conges.setType("Congés");


        Set<Event> events = new HashSet<>();
        events.add(conges);

        User Marco = new User();
        Marco.setUsername("Marco");
        Marco.setPassword("123456");
        User Polo = new User();
        Polo.setUsername("Polo");
        Polo.setPassword("654321");
        Marco.setEvents(events);


        userRepository.save(Marco);
        userRepository.save(Polo);


    }
}
