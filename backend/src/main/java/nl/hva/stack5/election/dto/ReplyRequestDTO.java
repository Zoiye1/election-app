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

    //Constructors
    public ReplyRequestDTO() {
    }

    public ReplyRequestDTO(String content, Integer discussionId, Integer userId) {
        this.content = content;
        this.discussionId = discussionId;
        this.userId = userId;
    }


}
