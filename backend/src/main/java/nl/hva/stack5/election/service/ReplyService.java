package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;

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
}
