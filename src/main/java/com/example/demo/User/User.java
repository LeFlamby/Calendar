package com.example.demo.User;

import com.example.demo.Event.Event;
import com.example.demo.Note.Note;
import com.example.demo.calendar.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("users") // en attendant le DTO
    private Set<Calendar> calendars = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("users")
    private Set<Calendar> ownedCalendar = new HashSet<>();

    @OneToMany(cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("users")
    private Set<Note> notes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("users")
    private Set<Event> events = new HashSet<>();



}
