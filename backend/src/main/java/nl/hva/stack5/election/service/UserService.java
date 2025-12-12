package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(int id);
    User createUser(User user);
    boolean verifyUsernameAndPassword(String username, String password);
    boolean verifyEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByUsername(String username);
}
