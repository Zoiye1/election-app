package nl.hva.stack5.election.controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.service.UserService;
import nl.hva.stack5.election.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nl.hva.stack5.election.service.VerificationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for user operations including authentication
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Retrieve user by ID
     * @param userId the user ID to fetch
     * @return User object if found, error response if not
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {
        logger.info("Fetching user with ID: {}", userId);
        Optional<User> user = userService.findById(userId);

        // Return 404 if user does not exist
        if (user.isEmpty()) {
            logger.warn("User not found with ID: {}", userId);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "USER_NOT_FOUND");
            errorResponse.put("message", "No user exists with ID: " + userId);
            errorResponse.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        logger.info("Successfully retrieved user with ID: {}", userId);
        return ResponseEntity.ok(user.get());
    }

    /**
     * Update username for existing user
     * @param userId the user ID
     * @param request request body with new username
     * @return Updated user
     */
    @PutMapping("/{userId}/username")
    public ResponseEntity<?> updateUsername(@PathVariable Integer userId, @RequestBody Map<String, String> request) {
        logger.info("Updating username for user ID: {}", userId);

        String newUsername = request.get("username");

        if (newUsername == null || newUsername.trim().isEmpty()) {
            logger.warn("Username update failed: empty username provided");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "INVALID_INPUT");
            errorResponse.put("message", "Username cannot be empty");
            errorResponse.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            User updated = userService.updateUsername(userId, newUsername);
            logger.info("Username updated successfully for user ID: {}", userId);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            logger.warn("Username update failed: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "UPDATE_FAILED");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Delete user account
     * @param userId the user ID to delete
     * @return Success message or error
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        logger.info("Deleting user with ID: {}", userId);

        try {
            userService.deleteUser(userId);
            logger.info("User deleted successfully with ID: {}", userId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("User deletion failed: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "USER_NOT_FOUND");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    /**
     * Create new user account
     * @param user the user object to create
     * @return Created user with 201 status
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        logger.info("Creating new user with email: {}", user.getEmail());

        if (result.hasErrors()) {
            logger.warn("Validation failed for user creation");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "VALIDATION_FAILED");
            errorResponse.put("message", "User data validation failed");
            errorResponse.put("status", 400);

            Map<String, String> fieldErrors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    fieldErrors.put(error.getField(), error.getDefaultMessage())
            );
            errorResponse.put("errors", fieldErrors);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        User created = userService.createUser(user);

        // Send verification email
        verificationService.sendVerificationEmail(created);

        logger.info("User created successfully with ID: {} and verification email sent", created.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Account created successfully. Please check your email to verify your account.");
        response.put("user", created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        logger.info("Email verification attempt with token");

        boolean verified = verificationService.verifyToken(token);

        if (verified) {
            logger.info("Email verified successfully");
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Email verified successfully. You can now log in.");
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Email verification failed: invalid or expired token");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "VERIFICATION_FAILED");
            errorResponse.put("message", "Invalid or expired verification token");
            errorResponse.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Verify user credentials and generate JWT token
     * @param user the user credentials to verify
     * @return JWT token and user data if valid, error if invalid
     */
    @PostMapping(value = "/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestBody User user) {
        String identifier = user.getEmail() != null ? user.getEmail() : user.getUsername();
        logger.info("Login attempt for: {}", identifier);

        User authenticatedUser = null;

        // Verify and retrieve full user object
        if (user.getEmail() != null) {
            authenticatedUser = userService.findByEmailAndVerifyPassword(user.getEmail(), user.getPassword());
        } else if (user.getUsername() != null) {
            authenticatedUser = userService.findByUsernameAndVerifyPassword(user.getUsername(), user.getPassword());
        }

        if (authenticatedUser != null) {
            // Check if user is verified
            if (!authenticatedUser.isVerified()) {
                logger.warn("Login blocked: user {} is not verified", authenticatedUser.getEmail());

                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "EMAIL_NOT_VERIFIED");
                errorResponse.put("message", "Please verify your email before logging in");
                errorResponse.put("status", 403);
                errorResponse.put("success", false);

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
            }

            // Generate token with userId
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", authenticatedUser.getId());
            String token = jwtUtil.generateToken(authenticatedUser.getEmail(), claims);
            logger.info("Successful login for user ID: {}", authenticatedUser.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", token);

            // Add user data WITH userId to response
            Map<String, Object> userData = new HashMap<>();
            userData.put("userId", authenticatedUser.getId());
            userData.put("email", authenticatedUser.getEmail());
            userData.put("username", authenticatedUser.getUsername());
            response.put("user", userData);

            return ResponseEntity.ok(response);
        }

        logger.warn("Failed login attempt for: {}", identifier);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "INVALID_CREDENTIALS");
        errorResponse.put("message", "Email/username or password is incorrect");
        errorResponse.put("status", 401);
        errorResponse.put("success", false);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}