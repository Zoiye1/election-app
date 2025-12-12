package nl.hva.stack5.election.dto;

/**
 * DTO for creating or updating a reply.
 * Contains only the data required from the client.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class ReplyRequestDTO {

    private String content;
    private Integer discussionId;
    private Integer userId;

    private Long parentReplyId;

    //Constructors
    public ReplyRequestDTO() {
    }

    public ReplyRequestDTO(String content, Integer discussionId, Integer userId) {
        this.content = content;
        this.discussionId = discussionId;
        this.userId = userId;
    }

    // GETTERS
    public String getContent() {
        return content;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getParentReplyId () {
        return parentReplyId;
    }

    //SETTERS
    public void setContent(String content) {
        this.content = content;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setParentReplyId (Long parentReplyId) {
        this.parentReplyId = parentReplyId;
    }
}
