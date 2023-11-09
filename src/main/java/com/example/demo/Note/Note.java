package com.example.demo.Note;


import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Note {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    Long id;

    String content;
    Date date;

    @ManyToOne
    @JsonIgnoreProperties("notes")
    private User user;
}
