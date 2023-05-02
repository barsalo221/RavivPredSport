package com.example.raviv.controller;

import com.example.raviv.exception.UserNotFoundException;
import com.example.raviv.model.User;
import com.example.raviv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
       return userRepository.save(newUser);
    }
    @GetMapping("/secured/users")
    List<User> getAllUsers(@CookieValue("userName") String userName) {
        String user = userName;
        return userRepository.findAll();
    }

    @GetMapping("/secured/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user/login")
    boolean login(@RequestBody User searchedUser){
        List<User> users =  userRepository.findAll().stream().toList();
        List<User> foundUserList = users.stream().filter((user)-> user.getUsername().equals(searchedUser.getUsername())).toList();

        if(foundUserList.isEmpty()){
            throw new UserNotFoundException();
        }
        User foundUser= foundUserList.get(0);
        return foundUser.getPassword().equals(searchedUser.getPassword());
    }

    @PutMapping("/secured/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/secured/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        String name = userRepository.findById(id).get().getUsername();

        userRepository.deleteById(id);
        String text = " deleted with. number id: " + id;
        return "User: " + name +text;
    }
    @GetMapping("/secured/data")//                @CookieValue("username") String userName,@CookieValue("password") String password
    public List<Map<String, Object>> getData(@CookieValue("userName") String userName,@CookieValue("password") String password) {
        User user  = new User(userName,password,"");
        if(login(user)){
            String sql = "SELECT * FROM nba_stats";
            return jdbcTemplate.queryForList(sql);
        }
        throw new UserNotFoundException();
    }


//    @GetMapping("/players")
//    List<NbaStats> getAllnbaStats() {
//        return nbaStatsRepository.findAll();
//    }
}
