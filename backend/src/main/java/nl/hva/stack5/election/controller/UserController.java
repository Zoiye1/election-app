package nl.hva.stack5.election.controller;
import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.service.UserService;
import nl.hva.stack5.election.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable Integer userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user.get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestBody User user) {
        boolean isValid = false;
        String identifier = null;

        if (user.getEmail() != null) {
            isValid = userService.verifyEmailAndPassword(user.getEmail(), user.getPassword());
            identifier = user.getEmail();
        } else if (user.getUsername() != null) {
            isValid = userService.verifyUsernameAndPassword(user.getUsername(), user.getPassword());
            identifier = user.getUsername();
        }

        if (isValid && identifier != null){

            String token = jwtUtil.generateToken(identifier);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", token);

//            User date frontend
            Map<String, Object> userData = new HashMap<>();
            userData.put("email",  user.getEmail());
            userData.put("username", user.getUsername());
            response.put("user", userData);

            return ResponseEntity.ok(response);

        }

//        Code when login is not successful
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}