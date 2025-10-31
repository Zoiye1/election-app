package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Manual repository interface for User entity (no Spring Data).
 */
public interface UserRepository {
    /**
     * Save a user entity.
     * @param user The user entity to save
     * @return The saved user entity
     */
    User save(User user);

    /**
     * Find a user by their ID
     * @param id The ID of the user
     * @return Optional containing the user if found
     */
    Optional<User> findById(Integer id);

    /**
     * Find a user by their email
     * @param email The email of the user
     * @return The user if found, null otherwise
     */
    User findByEmail(String email);

    /**
     * Find a user by their username
     * @param username The username of the user
     * @return The user if found, null otherwise
     */
    User findByUsername(String username);

    /**
     * Check if a user with the given email exists
     * @param email The email to check
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Check if a user with the given username exists
     * @param username The username to check
     * @return true if exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Find all users
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Delete a user entity
     * @param user The user entity to delete
     */
    void delete(User user);

    /**
     * Delete a user by their ID
     * @param id The ID of the user to delete
     */
    void deleteById(Integer id);
}