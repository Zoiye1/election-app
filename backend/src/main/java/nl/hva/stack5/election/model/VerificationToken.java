// package nl.hva.stack5.election.model;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "verification_token")
// public class VerificationToken {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @Column(name = "token", nullable = false, unique = true)
//     private String token;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @Column(name = "created_at", nullable = false)
//     private LocalDateTime createdAt;

//     @Column(name = "expires_at", nullable = false)
//     private LocalDateTime expiresAt;

//     @Column(name = "used", nullable = false)
//     private boolean used = false;

//     public VerificationToken() {}

//     public VerificationToken(String token, User user, LocalDateTime expiresAt) {
//         this.token = token;
//         this.user = user;
//         this.createdAt = LocalDateTime.now();
//         this.expiresAt = expiresAt;
//         this.used = false;
//     }

//     public Integer getId() {
//         return id;
//     }

//     public void setId(Integer id) {
//         this.id = id;
//     }

//     public String getToken() {
//         return token;
//     }

//     public void setToken(String token) {
//         this.token = token;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }

//     public LocalDateTime getExpiresAt() {
//         return expiresAt;
//     }

//     public void setExpiresAt(LocalDateTime expiresAt) {
//         this.expiresAt = expiresAt;
//     }

//     public boolean isUsed() {
//         return used;
//     }

//     public void setUsed(boolean used) {
//         this.used = used;
//     }

//     public boolean isExpired() {
//         return LocalDateTime.now().isAfter(this.expiresAt);
//     }
// }