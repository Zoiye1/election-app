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
    private Integer id;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private Integer discussionId;


    // Constructors

    public ReplyResponseDTO() {}

    public ReplyResponseDTO(Integer id, String content, String authorName, LocalDateTime createdAt, Integer discussionId) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.discussionId = discussionId;
    }
}
