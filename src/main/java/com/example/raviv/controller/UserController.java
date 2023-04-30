package com.example.raviv.controller;

import com.example.raviv.exception.UserNotFoundException;
import com.example.raviv.model.User;
import com.example.raviv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
       return userRepository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user/login")
    boolean login(@RequestBody User searchedUser){
        List<User> users =  userRepository.findAll().stream().toList();
        List<User> foundUserList = users.stream().filter(_user -> _user.getEmail()==searchedUser.getEmail()).toList();
        if(foundUserList.isEmpty()){
            throw new UserNotFoundException((long) -1);
        }
        User foundUser= foundUserList.get(0);
        return foundUser.getPassword()==searchedUser.getPassword();
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        String name = userRepository.findById(id).get().getUsername();

        userRepository.deleteById(id);
        String text = " deleted with. number id: " + id;
        return "User: " + name +text;
    }
//    @GetMapping("/players")
//    List<NbaStats> getAllnbaStats() {
//        return nbaStatsRepository.findAll();
//    }
}
