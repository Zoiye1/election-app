// package nl.hva.stack5.election.service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.*;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Service
// public class EmailService {

//     private static final Logger log = LoggerFactory.getLogger(EmailService.class);
//     private static final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";

//     @Value("${election.frontend.url}")
//     private String frontendUrl;

//     @Value("${brevo.api.key}")
//     private String brevoApiKey;

//     private final RestTemplate restTemplate;

//     public EmailService() {
//         this.restTemplate = new RestTemplate();
//     }

//     public void sendVerificationEmail(String toEmail, String token) {
//         try {
//             log.info("Sending verification email to: {}", toEmail);

//             String verificationUrl = frontendUrl + "/verify-email?token=" + token;

//             String htmlContent = "<html><body>" +
//                     "<h2>Verify your email address</h2>" +
//                     "<p>Click the link below to verify your email address:</p>" +
//                     "<p><a href=\"" + verificationUrl + "\">Verify Email</a></p>" +
//                     "<p>Or copy this link: " + verificationUrl + "</p>" +
//                     "<p>This link expires in 24 hours.</p>" +
//                     "</body></html>";

//             sendBrevoEmail(toEmail, "Verify your email address", htmlContent);

//             log.info("Verification email sent successfully to: {}", toEmail);

//         } catch (Exception e) {
//             log.error("Failed to send verification email to: {}. Error: {}", toEmail, e.getMessage(), e);
//             throw new RuntimeException("Failed to send verification email", e);
//         }
//     }

//     private void sendBrevoEmail(String to, String subject, String htmlContent) {
//         try {
//             Map<String, Object> requestBody = new HashMap<>();

//             Map<String, String> sender = new HashMap<>();
//             sender.put("email", "noreply@election.com");
//             sender.put("name", "StemSlim");
//             requestBody.put("sender", sender);

//             Map<String, String> recipient = new HashMap<>();
//             recipient.put("email", to);
//             requestBody.put("to", List.of(recipient));

//             requestBody.put("subject", subject);
//             requestBody.put("htmlContent", htmlContent);

//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.APPLICATION_JSON);
//             headers.set("api-key", brevoApiKey);

//             HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

//             ResponseEntity<String> response = restTemplate.exchange(
//                     BREVO_API_URL,
//                     HttpMethod.POST,
//                     request,
//                     String.class
//             );

//             if (!response.getStatusCode().is2xxSuccessful()) {
//                 log.error("Brevo API returned error status: {}", response.getStatusCode());
//                 throw new RuntimeException("Failed to send email via Brevo API");
//             }

//         } catch (Exception e) {
//             log.error("Error sending email via Brevo API: {}", e.getMessage(), e);
//             throw new RuntimeException("Failed to send email via Brevo API", e);
//         }
//     }
// }