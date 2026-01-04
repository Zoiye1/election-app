package nl.hva.stack5.election.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entity representing a user in the election system
 * Stores user credentials and basic information
 */
@Entity
@Table(name = "\"user\"")
public class User {

    // Primary key, auto-generated
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Username must be unique and between 3-50 characters
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "username", unique = true)
    private String username;

    // Email must be unique and valid format
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(name = "email", unique = true)
    private String email;

    // Password must be at least 8 characters with uppercase, lowercase, digit and special character
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
    @Column(name = "password")
    private String password;

    // Email verification status
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    /**
     * Default constructor required by JPA
     */
    public User() {}

    /**
     * Constructor to create user with credentials
     * @param username the username
     * @param email the email address
     * @param password the password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isVerified = false;
    }

    /**
     * Get user ID
     * @return user ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set user ID
     * @param id the user ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get email address
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email address
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Check if user email is verified
     * @return true if verified, false otherwise
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Set email verification status
     * @param verified verification status
     */
    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}