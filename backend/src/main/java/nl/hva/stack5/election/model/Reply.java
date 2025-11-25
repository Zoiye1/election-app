package nl.hva.stack5.election.model;

import java.time.LocalDateTime;

public class Reply {
    private Integer id;
    private String content;
    private Discussion discussion;
    private User author;
    private LocalDateTime createdAt;

    //default constructor
    public Reply() {
        this.createdAt = LocalDateTime.now();
    }


    public Reply(String content, Discussion discussion, User author) {
        this.content = content;
        this.discussion = discussion;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    //GETTERS
    public Integer getId() {
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

    //SETTERS
    public void setId(Integer id) {
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
