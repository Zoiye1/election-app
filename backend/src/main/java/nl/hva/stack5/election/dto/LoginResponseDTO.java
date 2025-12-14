package nl.hva.stack5.election.dto;

public class LoginResponseDTO {

    private boolean success;
    private String token;
    private UserResponseDTO user;

    public LoginResponseDTO() {}

    public LoginResponseDTO(boolean success, String token, UserResponseDTO user) {
        this.success = success;
        this.token = token;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}