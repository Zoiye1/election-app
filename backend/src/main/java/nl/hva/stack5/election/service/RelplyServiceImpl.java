package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ReplyMapper;
import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;
import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.repository.DiscussionRepository;
import nl.hva.stack5.election.repository.ReplyRepository;
import nl.hva.stack5.election.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ReplyService Interface.
 * Handles business logic for Reply operations.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Service
public class RelplyServiceImpl implements ReplyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyMapper replyMapper;


    // ==== CREATE ====

    /**
     * Creates a new reply.
     *
     * @param dto the reply request data
     * @return the created reply as DTO
     */
    @Override
    public ReplyResponseDTO createReply(ReplyRequestDTO dto) {
        // get discussion from database
        Discussion discussion = discussionRepository.findById(dto.getDiscussionId())
                .orElseThrow(() -> new RuntimeException("Discussion not found with id: " + dto.getDiscussionId()));
        // get the user from database
        User user = userRepository.findById(dto.getDiscussionId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getDiscussionId()));
        // convert dto to entity
        Reply reply = replyMapper.toEntity(dto, discussion, user);
        // save reply
        Reply savedReply = replyRepository.Create(reply);
        //return as DTO
        return replyMapper.toDTO(savedReply);
    }

    // ==== READ ====

    /**
     * Gets a reply by its ID.
     *
     * @param id the reply ID
     * @return the reply as DTO
     */
    @Override
    public ReplyResponseDTO getReplyById(long id) {
        // get reply from database
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found with id: " + id));

        return replyMapper.toDTO(reply);
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
