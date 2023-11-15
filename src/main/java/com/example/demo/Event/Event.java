package com.example.demo.Event;

import com.example.demo.Style.Style;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String type;
    String title;
    Date start;
    Date end;
    // length
    String description;

    @ManyToMany(mappedBy = "events")
    @JsonIgnoreProperties("events")
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("event")
    private Set<Style> styles = new HashSet<>();
}


