package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;

import java.util.List;

/**
 * Service interface for Reply operations.
 * Defines business logic methods for managing replies.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface ReplyService {

    // ===== CREATE =====

    /**
     * Creates a new reply.
     *
     * @param dto the reply request data
     * @return the created reply as DTO
     */
    ReplyResponseDTO createReply(ReplyRequestDTO dto);

    // ===== READ =====

    /**
     * Gets a reply by its ID.
     *
     * @param id the reply ID
     * @return the reply as DTO
     */
    ReplyResponseDTO getReplyById(long id);

    /**
     * Gets all replies for a specific discussion.
     *
     * @param discussionId the discussion ID
     * @return list of replies as DTOs
     */
    List<ReplyResponseDTO> getRepliesByDiscussionId(Integer discussionId);

    /**
     * Gets all replies by a specific user.
     *
     * @param authorId the user ID
     * @return list of replies as DTOs
     */
    List<ReplyResponseDTO> getRepliesByAuthorId(long authorId);

    /**
     * Gets the total count of replies for a discussion.
     *
     * @param discussionId the discussion ID
     * @return number of replies
     */
    Long getReplyCount(Integer discussionId);

    // ===== UPDATE =====

    /**
     * Updates an existing reply.
     *
     * @param id the reply ID
     * @param dto the updated reply data
     * @return the updated reply as DTO
     */
    ReplyResponseDTO updateReply(Long id, ReplyRequestDTO dto);

    // ===== DELETE =====

    /**
     * Deletes a reply by its ID.
     *
     * @param id the reply ID
     */
    void deleteReply(Long id);
}
