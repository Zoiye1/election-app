package nl.hva.stack5.election.dto;


import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Reply entities and DTOs.
 * Handles the conversion logic for request and response objects.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Component
public class ReplyMapper {


    /**
     * Converts a Reply entity to a ReplyResponseDTO.
     * Used when sending reply data to the client.
     *
     * @param reply the reply entity
     * @return ReplyResponseDTO with reply data
     */
    public ReplyResponseDTO toDTO(Reply reply) {
        if (reply == null) {
            return null;
        }

        return new ReplyResponseDTO(
                reply.getId(),
                reply.getContent(),
                reply.getAuthor().getUsername(),
                reply.getAuthor().getId(),
                reply.getCreatedAt(),
                reply.getDiscussion().getId(),     // Gets the discussion id from discussion
                reply.getParentReply() != null ? reply.getParentReply().getId() : null
        );
    }

    /**
     * Converts a ReplyRequestDTO to a Reply entity with Discussion and User.
     *
     * @param dto the request DTO
     * @param discussion the discussion entity
     * @param user the user entity
     * @return Reply entity with all relationships set
     */
    public Reply toEntity(ReplyRequestDTO dto, Discussion discussion, User user, Reply parentReply) {
        if (dto == null) {
            return null;
        }

        return new Reply(
                dto.getContent(),
                discussion,
                user,
                parentReply
        );
    }

}
