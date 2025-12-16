package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final Argon2PasswordEncoder passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean verifyUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) return false;
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean verifyEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Update username for a user
     * @param userId the user ID
     * @param newUsername the new username
     * @return updated User
     */
    @Override
    public User updateUsername(Integer userId, String newUsername) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        if (userRepository.existsByUsername(newUsername)) {
            throw new IllegalArgumentException("Username already exists: " + newUsername);
        }

        User user = userOpt.get();
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    /**
     * Delete user by ID
     * @param userId the user ID to delete
     */
    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.findById(userId).isPresent()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}