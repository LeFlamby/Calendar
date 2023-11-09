package com.example.demo.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private Long id;
    private String username;

}
