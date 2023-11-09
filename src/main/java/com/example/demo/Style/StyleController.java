package com.example.demo.Style;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
@RequiredArgsConstructor
public class StyleController {

    private final StyleService service;

    @GetMapping
    ResponseEntity<List<Style>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Style> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Style> create(@RequestBody Style style){
        return new ResponseEntity<>(service.create(style), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    ResponseEntity<Style> update(@PathVariable Long id, @RequestBody Style style){
        return new ResponseEntity<>(service.update(id, style), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
