package com.example.raviv.controller;

import com.example.raviv.exception.UserExistsException;
import com.example.raviv.exception.UserNotFoundException;
import com.example.raviv.exception.UserNotFullException;
import com.example.raviv.model.Player;
import com.example.raviv.model.User;
import com.example.raviv.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000/" ,allowCredentials = "true")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;



    @PostMapping("/secured/try")
    public String tryFunc(){
        return "try";
    }

    @PostMapping("/secured/predictstats")
    public String createPlayerFile(@RequestBody Player[] jsonArr){
        ObjectMapper objectMapper = new ObjectMapper();
//
//        for (Player json : jsonArr) {
//            try {
//                // parse the JSON object
//                Object jsonObject = objectMapper.readValue(json, Object.class);
//
//                // create a new file with a unique filename
//                String filename = "C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model\\sumbit.txt";
//                File file = new File(filename);
//
//                // write the JSON object to the file
//                FileWriter fileWriter = new FileWriter(file);
//                objectMapper.writeValue(fileWriter, jsonObject);
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "Error: " + e.getMessage();
//            }
//        }

        return "Files created successfully";
    }
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        List<User> users =  userRepository.findAll().stream().toList();
        for (User user: users) {
            if(user.getUsername().equals(newUser.getUsername())) {
                throw new UserExistsException();
            }
            if(newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty()||newUser.getEmail().isEmpty()){
                throw new UserNotFullException();
            }
        }
        return userRepository.save(newUser);
    }

    @GetMapping("/secured/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/secured/user/{id}")
    User getUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user/login")
    boolean login(@RequestBody User searchedUser){
        return authenticate(searchedUser.getUsername(),searchedUser.getPassword());
    }
    public boolean authenticate(String userName, String password){
        List<User> users =  userRepository.findAll().stream().toList();
        List<User> foundUserList = users.stream().filter((user)-> user.getUsername().equals(userName)).toList();

        if(foundUserList.isEmpty()){
            throw new UserNotFoundException();
        }
        User foundUser= foundUserList.get(0);
        boolean isSameUser  =foundUser.getPassword().equals(password);
        System.out.println(isSameUser);
        return isSameUser;
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
        return "User: " + name + text;
    }
    @GetMapping("/secured/data")//                @CookieValue("username") String userName,@CookieValue("password") String password
    public List<Map<String, Object>> getData() {
        String sql = "SELECT * FROM nba_stats";
        return jdbcTemplate.queryForList(sql);
        }
    @GetMapping("/secured/data22")
    public List<Map<String, Object>> getData22(){
        String sql = "SELECT * FROM nba_stats22";
        return jdbcTemplate.queryForList(sql);
    }
    @GetMapping("/secured/data21")
    public List<Map<String, Object>> getData21(){
        String sql = "SELECT * FROM nba_stats21";
        return jdbcTemplate.queryForList(sql);
    }

//    @PostMapping("/secured/predictstats")
    public String getPredictedStats() throws IOException, InterruptedException {
        String dir = "C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model";
        File pla = new File("submit.txt", "w");

        String command = "python main.py  path, pathres";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command );
        StringBuilder output = new StringBuilder();
        builder.directory(new File(dir));

        //builder.redirectErrorStream(true);
        Process process = builder.start();

        // Read output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        reader.close();

        // Wait for process to finish
        int exitCode = process.waitFor();
        System.out.println("Process exited with code " + exitCode);
        return output.toString();

    }


}
