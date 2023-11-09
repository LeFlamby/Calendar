package com.example.demo.calendar;


import com.example.demo.User.User;
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
public class Calendar {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    Long id;

    String code;
    //Long owner;

    @ManyToMany(mappedBy = "calendars")
    @JsonIgnoreProperties("calendars")
    private Set<User> Users = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("calendars")
    private User user;

}
