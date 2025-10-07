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

    /**
     * @param id
     * @return User if exists
     */
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    /**
     * @param user
     * @return User if successfully created
     */
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * @param username
     * @param password
     * @return true if combination matches a user in the database
     */
    @Override
    public boolean verifyUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());    }

    /**
     * @param email
     * @param password
     * @return true if combination matches a user in the database
     */
    @Override
    public boolean verifyEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        return passwordEncoder.matches(password, user.getPassword());    }
}
