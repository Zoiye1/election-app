package nl.hva.stack5.election.dto;


import java.time.LocalDateTime;

/**
 * DTO for reply responses sent to the client.
 * Contains reply data with author information.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class ReplyResponseDTO {
    private Long id;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private Integer discussionId;
    private Long parentReplyId;


    // Constructors

    public ReplyResponseDTO() {}

    public ReplyResponseDTO(Long id, String content, String authorName, LocalDateTime createdAt, Integer discussionId, Long parentReplyId) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.discussionId = discussionId;
        this.parentReplyId = parentReplyId;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public Long getParentReplyId() {
        return parentReplyId;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
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

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public void setParentReplyId(Long parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

}

