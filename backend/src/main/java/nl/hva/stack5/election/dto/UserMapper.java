package nl.hva.stack5.election.dto;

import nl.hva.stack5.election.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}