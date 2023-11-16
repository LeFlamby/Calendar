package com.example.demo.User_Event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bind")
@RequiredArgsConstructor
public class UserEventController {

    private final UserEventService userEventService;


    @PostMapping("/{eventId}/user/{userId}")
    ResponseEntity<Map<String, String>> bindEventToUser(@PathVariable Long eventId, @PathVariable Long userId) {
        Map<String, String> response = new HashMap<>();

        try {
            userEventService.bindEventToUser(eventId, userId) ;
            response.put("Result" , "Event successfully bound to user");
            return new ResponseEntity<>(response , HttpStatus.OK);
        } catch (Exception e) {
            response.put( "Result" , e.getMessage());
            return new ResponseEntity<>(response,  HttpStatus.BAD_REQUEST);
        }
    }
}


