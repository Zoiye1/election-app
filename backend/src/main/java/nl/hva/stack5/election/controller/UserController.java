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

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil; // JWT utility for token generation

    // Get user by ID
    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable Integer userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user.get();
    }

    // Create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Verify user credentials and return JWT token
    @PostMapping(value = "/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestBody User user) {
        boolean isValid = false;
        User authenticatedUser = null;

        // Check credentials via email or username
        if (user.getEmail() != null) {
            isValid = userService.verifyEmailAndPassword(user.getEmail(), user.getPassword());
            if (isValid) {
                authenticatedUser = userService.findByEmail(user.getEmail());
            }
        } else if (user.getUsername() != null) {
            isValid = userService.verifyUsernameAndPassword(user.getUsername(), user.getPassword());
            if (isValid) {
                authenticatedUser = userService.findByUsername(user.getUsername());
            }
        }

        // If credentials are valid, generate JWT token with userId
        if (isValid && authenticatedUser != null) {
            // Create claims map with userId
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", authenticatedUser.getId());

            // Generate JWT token with userId in claims
            String token = jwtUtil.generateToken(authenticatedUser.getEmail(), claims);

            // Build success response
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", token);

            // Add user data for frontend
            Map<String, Object> userData = new HashMap<>();
            userData.put("email", authenticatedUser.getEmail());
            userData.put("username", authenticatedUser.getUsername());
            userData.put("id", authenticatedUser.getId()); // Also include ID in response
            response.put("user", userData);

            return ResponseEntity.ok(response);
        }

        // Return error if login failed
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", "Invalid credentials");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}