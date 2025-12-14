package nl.hva.stack5.election.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Candidate represents an individual reply under a discussion,
 * storing the content with a unique identifier.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT" )
    private String content;

    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_reply_id")
    private Reply parentReply;

    //default constructor
    public Reply() {}


    public Reply(String content, Discussion discussion, User author, Reply parentReply) {
        this.content = content;
        this.discussion = discussion;
        this.author = author;
        this.parentReply = parentReply;
        this.createdAt = LocalDateTime.now();
    }

    //GETTERS
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Reply getParentReply() {
        return parentReply;
    }

    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setParentReply (Reply parentReply) {
        this.parentReply = parentReply;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
