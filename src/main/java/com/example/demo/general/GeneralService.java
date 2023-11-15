package com.example.demo.general;

import com.example.demo.Event.Event;
import com.example.demo.Event.EventRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




@Service
@RequiredArgsConstructor
public class GeneralService {

    private final BCryptPasswordEncoder bcryptEncoder;




    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;
    public void setUp() {

        Event conges = new Event();
        conges.setTitle("All-day event");
        conges.setStart(new Date(123 , 11 , 8));
        conges.setEnd(new Date(123 , Calendar.NOVEMBER , 11));
        conges.setDescription("Congés de Noël");
        conges.setType("Congés");


        Set<Event> events = new HashSet<>();
        events.add(conges);
        //String hashedPassword = bcryptEncoder.encode(user.getPassword());
        User Marco = new User();
        Marco.setUsername("Marco");
        Marco.setPassword(bcryptEncoder.encode("123456"));
        Marco.setEvents(events);




        userRepository.save(Marco);


    }
}
