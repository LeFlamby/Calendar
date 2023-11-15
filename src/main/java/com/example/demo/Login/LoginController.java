package com.example.demo.Login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.User.UserRepository;

import com.example.demo.User.User;
import com.example.demo.general.JWT.jwtUtil;
import com.example.demo.general.ApiResponse;

@Controller
public class LoginController {



    private final jwtUtil jwtUtil;
    private final LoginService loginService;

    @Autowired
    private  final UserRepository userRepository;

    public LoginController(com.example.demo.general.JWT.jwtUtil jwtUtil, LoginService loginService, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.loginService = loginService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register") // sert à enregistrer un user dans la base de donnée
    public ResponseEntity<Map<String, Object>> postUser(@RequestBody User user) { // on récupère le body de la requête
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // on encode le password
        userRepository.save(user);// on sauvegarde le user dans la base de donnée
        Map<String, Object> data = new HashMap<>();
        data.put("Request", "User saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody User user) { // l'api réponse est un objet qui
        // contient un objet et un message
        HashMap<String, Object> data = new HashMap<>(); // le hashmap est un objet qui contient des clés et des valeurs
        try {
            loginService.login(user); // on recupère le user
            String token = jwtUtil.generateToken(user); // on génère un token
            // data.put renvoie la valeur associée à la clé spécifiée

            data.put("token", token);
            data.put("Request", "User logged in");
            return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

