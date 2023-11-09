package com.example.demo.Login;


import java.util.regex.Pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;


@RequiredArgsConstructor
@Service
public class LoginService {
    private final BCryptPasswordEncoder bcryptEncoder;
    private final UserRepository userRepository;


    public boolean checkHashedPassword(String password) {
        if (password != null) {
            Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,}");
            return pattern.matcher(password).matches();
        } else {
            throw new RuntimeException("Aucun mot de passe trouvé");
        }
    }

    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return bcryptEncoder.matches(password, hashedPassword);
    }

    public User getUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Le pseudo n'existe pas"));
    }

    public User login(User user) {
        User existingUser = getUsername(user.getUsername());

        if (!checkPassword(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        existingUser.setPassword("hidden");

        return existingUser;
    }
}
