package nl.hva.stack5.election.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String token) {
        // FRONTEND URL, niet backend
        String verificationUrl = "http://localhost:5173/verify-account?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verify your email address");
        message.setText("Click the link below to verify your email address:\n\n" + verificationUrl + "\n\nThis link expires in 24 hours.");
        message.setFrom("noreply@election.com");

        mailSender.send(message);
    }
}