package com.example.demo.calendar;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService service;

    @GetMapping
    ResponseEntity<List<Calendar>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
@GetMapping ("/{id}")
ResponseEntity<Calendar> getById(@PathVariable Long id){
    return ResponseEntity.ok(service.getById(id));
}

@PostMapping
ResponseEntity<Calendar> create(@RequestBody Calendar calendar) {
    return ResponseEntity.ok(service.create(calendar));
}

@PutMapping("/{id}")
ResponseEntity<Calendar> update(@PathVariable Long id, @RequestBody Calendar calendar) {
    return ResponseEntity.ok(service.update(id, calendar));
}

@DeleteMapping("/{id}")
ResponseEntity<?> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
}

}


