package nl.hva.stack5.election.dto;

import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper to convert between Discussion entities and DTOs
 */
@Component
public class DiscussionMapper {

    /**
     * Convert Discussion entity to DiscussionResponseDTO
     * Used for: GET endpoints (Entity from database → DTO to client)
     *
     * @param discussion The Discussion entity from database
     * @return DiscussionResponseDTO for API response
     */
    public DiscussionResponseDTO toResponseDTO(Discussion discussion) {
        if (discussion == null) {
            return null;
        }

        DiscussionResponseDTO dto = new DiscussionResponseDTO();

        // Simple fields: direct copy
        dto.setId(discussion.getId());
        dto.setTitle(discussion.getTitle());
        dto.setContent(discussion.getContent());
        dto.setCreatedAt(discussion.getCreatedAt());
        dto.setViews(discussion.getViews());
        dto.setReplies(discussion.getReplies());

        // Complex field: User object → Simple fields
        if (discussion.getAuthor() != null) {
            dto.setAuthorName(discussion.getAuthor().getUsername());
            dto.setAuthorId(discussion.getAuthor().getId());
        }

        return dto;
    }

    /**
     * Convert DiscussionRequestDTO to Discussion entity
     * Used for: POST endpoints (DTO from client → Entity for database)
     *
     * Note: Author must be set separately in the service layer using authorId
     *
     * @param dto The DiscussionRequestDTO from client
     * @return Discussion entity (without author, that's set in service)
     */
    public Discussion toEntity(DiscussionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Discussion discussion = new Discussion();
        discussion.setTitle(dto.getTitle());
        discussion.setContent(dto.getContent());

        // Note: Author is NOT set here!
        // The service layer will fetch the User by authorId and set it

        return discussion;
    }

    /**
     * Update existing Discussion entity with data from DiscussionRequestDTO
     * Used for: PUT endpoints (update existing entity)
     *
     * @param dto The DiscussionRequestDTO with new data
     * @param discussion The existing Discussion entity to update
     */
    public void updateEntityFromDTO(DiscussionRequestDTO dto, Discussion discussion) {
        if (dto == null || discussion == null) {
            return;
        }

        discussion.setTitle(dto.getTitle());
        discussion.setContent(dto.getContent());
    }
}