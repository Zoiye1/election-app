package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;

import java.util.List;

/**
 * Implementation of ReplyService Interface.
 * Handles business logic for Reply operations.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class RelplyServiceImpl implements ReplyService {

    // ==== CREATE ====

    @Override
    public ReplyResponseDTO createReply(ReplyRequestDTO dto) {
        return null;
    }

    // ==== READ ====

    @Override
    public ReplyResponseDTO getReplyById(long id) {
        return null;
    }

    @Override
    public List<ReplyResponseDTO> getRepliesByDiscussionId(Integer discussionId) {
        return List.of();
    }

    @Override
    public List<ReplyResponseDTO> getRepliesByUserId(Integer userId) {
        return List.of();
    }

    @Override
    public Long getReplyCount(Integer discussionId) {
        return 0L;
    }

    // ==== UPDATE ====

    @Override
    public ReplyResponseDTO updateReply(Long id, ReplyRequestDTO dto) {
        return null;
    }

    // ==== DELETE ====

    @Override
    public void deleteReply(Long id) {

    }
}
