package com.example.demo.Event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor

public class EventController {

    private final EventService service;

    @GetMapping
    ResponseEntity<List<Event>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);}

    @GetMapping ("/{id}")
    ResponseEntity<Event> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Event> create(@RequestBody Event event) {
        return new ResponseEntity<>(service.create(event), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        return new ResponseEntity<>(service.update(id, event), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    }