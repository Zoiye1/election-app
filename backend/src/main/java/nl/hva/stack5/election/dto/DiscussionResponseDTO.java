package nl.hva.stack5.election.dto;

import java.time.LocalDateTime;

/**
 * DTO for returning Discussion data to the client
 * Used for GET responses
 */
public class DiscussionResponseDTO {
    private Integer id;
    private String title;
    private String content;
    private String authorName;
    private Integer authorId;
    private LocalDateTime createdAt;
    private Integer views;
    private Integer replies;

    public DiscussionResponseDTO(Integer id, String title, String content, String authorName, Integer authorId, LocalDateTime createdAt, Integer views, Integer replies) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.views = views;
        this.replies = replies;

    }

    public DiscussionResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getReplies() {
        return replies;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public void setReplies(Integer replies) {
        this.replies = replies;
    }


}
