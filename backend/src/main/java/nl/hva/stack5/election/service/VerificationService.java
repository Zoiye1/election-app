package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.model.VerificationToken;
import nl.hva.stack5.election.repository.UserRepository;
import nl.hva.stack5.election.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class VerificationService {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public String createVerificationToken(User user) {
        String token = generateSecureToken();
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);

        VerificationToken verificationToken = new VerificationToken(token, user, expiresAt);
        tokenRepository.save(verificationToken);

        return token;
    }

    @Transactional
    public boolean verifyToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElse(null);

        if (verificationToken == null) {
            return false;
        }

        if (verificationToken.isUsed()) {
            return false;
        }

        if (verificationToken.isExpired()) {
            return false;
        }

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        verificationToken.setUsed(true);
        tokenRepository.save(verificationToken);

        return true;
    }

    public void sendVerificationEmail(User user) {
        String token = createVerificationToken(user);
        emailService.sendVerificationEmail(user.getEmail(), token);
    }

    private String generateSecureToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}