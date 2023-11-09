package com.example.demo.User;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

   public User getById(Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("not found" + id));
        }

   public User create(User user){
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User userToUpdate = getById(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }

   public void delete(Long id) {
        userRepository.deleteById(id);
    }
    }




    


