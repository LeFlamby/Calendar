package com.example.demo.Style;


import com.example.demo.Event.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String backgroundColor;
    private String textColor;
    private String borderColor;

    @ManyToOne
    @JsonIgnoreProperties("styles")
    private Event event;
}
