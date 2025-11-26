package nl.hva.stack5.election.dto;


import nl.hva.stack5.election.model.Reply;
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
                reply.getAuthor().getUsername(),  // Gets the username from user
                reply.getCreatedAt(),
                reply.getDiscussion().getId()     // Gets the discussion id from discussion
        );
    }


    /**
     *Converts DTO to reply entity
     * Note: Discussion and User objects must be set separately in the service.
     *
     * @param dto The request DTO
     * @return Reply entity (without discussions and UserObjects set)
     */
    public Reply toEntity(ReplyRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Reply reply = new Reply();
        reply.setContent(dto.getContent());

        return reply;
    }

}
