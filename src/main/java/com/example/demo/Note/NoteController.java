package com.example.demo.Note;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {


    private final NoteService service;


    @GetMapping
    ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    ResponseEntity<Note> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok(service.create(note));
    }
    @PutMapping("{id}")
    ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        return ResponseEntity.ok(service.update(id, note));
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
