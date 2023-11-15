package com.example.demo.User;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bcryptEncoder;

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + id));

        UserDTO userDetailsDTO = new UserDTO();
        userDetailsDTO.setId(user.getId());
        userDetailsDTO.setUsername(user.getUsername());

        return userDetailsDTO;
    }

   public User create(User user){
        return userRepository.save(user);
    }


    public UserDTO update(Long id, User user) {
        User userToUpdate = userRepository.findById(id).get();
        if( user.getUsername() != null) {
            userToUpdate.setUsername(user.getUsername());
        }
        if ( user.getPassword() != null){
            String hashedPassword = bcryptEncoder.encode(user.getPassword());
            userToUpdate.setPassword(hashedPassword);}
        userRepository.save(userToUpdate);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userToUpdate.getUsername());
     return userDTO;
    }

   public void delete(Long id) {
        userRepository.deleteById(id);
    }
    }




    


