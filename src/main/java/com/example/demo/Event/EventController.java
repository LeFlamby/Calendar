package com.example.demo.Event;

import com.example.demo.general.JWT.jwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor

public class EventController {

    private final EventService service;
   private final jwtUtil jwtUtil;


    @GetMapping
    ResponseEntity<List<Event>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);}

    @GetMapping ("/{id}")
   ResponseEntity<Event> getById(@PathVariable Long id){
       return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

   @GetMapping("/user/{id}")
  public  ResponseEntity<List<Event>>getById(@PathVariable Long id, HttpServletRequest request) throws AccessDeniedException {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals(username)){
             return new ResponseEntity<>(service.getAllEventsByUserId(id), HttpStatus.OK);
         }
         else{
             throw new AccessDeniedException("Vous n'avez pas le droit d'accéder à cette ressource");
         }
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