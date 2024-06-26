package com.example.raviv.controller;

import com.example.raviv.exception.UserExistsException;
import com.example.raviv.exception.UserNotFoundException;
import com.example.raviv.exception.UserNotFullException;
import com.example.raviv.model.User;
import com.example.raviv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000/", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model\\submit%d.txt}")
    private String playersFile;
    @Value("${C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model}")
    private String pythonScriptDir;

    private String command = "python predict.py";



    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        List<User> users = userRepository.findAll().stream().toList();
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                throw new UserExistsException();
            }
            if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty() || newUser.getEmail().isEmpty()) {
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
    User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user/login")
    boolean login(@RequestBody User searchedUser) {
        return authenticate(searchedUser.getUsername(), searchedUser.getPassword());
    }

    public boolean authenticate(String userName, String password) {
        List<User> users = userRepository.findAll().stream().toList();
        List<User> foundUserList = users.stream().filter((user) -> user.getUsername().equals(userName)).toList();

        if (foundUserList.isEmpty()) {
            throw new UserNotFoundException();
        }
        User foundUser = foundUserList.get(0);
        boolean isSameUser = foundUser.getPassword().equals(password);
        System.out.println(isSameUser);
        return isSameUser;
    }

    //sdasdasd
    @PutMapping("/secured/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/secured/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        String name = userRepository.findById(id).get().getUsername();
        userRepository.deleteById(id);
        String text = " deleted with. number id: " + id;
        return "User: " + name + text;
    }
    @GetMapping("/secured/data24")
//                @CookieValue("username") String userName,@CookieValue("password") String password
    public List<Map<String, Object>> getData24() {
        String sql = "SELECT * FROM predsport.stats2024";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/secured/data23")
//                @CookieValue("username") String userName,@CookieValue("password") String password
    public List<Map<String, Object>> getData23() {
        String sql = "SELECT * FROM predsport.stats2023";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/secured/data22")
    public List<Map<String, Object>> getData22() {
        String sql = "SELECT * FROM predsport.stats2022";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/secured/data21")
    public List<Map<String, Object>> getData21() {
        String sql = "SELECT * FROM predsport.stats2021";
        return jdbcTemplate.queryForList(sql);
    }

    public void createFileInPath(String[] data, String pathFile) {
        try {
            String joined = "";
            for (String s : data) {
                joined = joined + s + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile));
            writer.write(joined);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> runCmdPython() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.directory(new File(pythonScriptDir));

        Process process = builder.start();

        // Read output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
        }

    @PostMapping("/secured/predictstats")
    public List<String> getPredictedStats(@RequestBody String[][] data) throws IOException {
        String filePath;
        Integer i = 1;
        for (String[] player : data) {
            filePath = String.format(playersFile, i);
            createFileInPath(player, filePath);
            i++;
        }
        List<String> output = runCmdPython();
        return output;
    }

    @GetMapping("/secured/displaystats")
    public List<String> displayStats() throws IOException {
        List<String> output = runCmdPython();
        return output;
    }
}