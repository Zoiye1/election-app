package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(int id);
    User createUser(User user);
    User findByEmailAndVerifyPassword(String email, String password);
    User findByUsernameAndVerifyPassword(String username, String password);
    User updateUsername(Integer userId, String newUsername);
    void deleteUser(Integer userId);
    User save(User user); // VOEG DEZE TOE
}