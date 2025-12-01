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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = userRepository.findById(dto.getUserId())
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

    /**
     * Gets all replies for a specific discussion.
     *
     * @param discussionId the discussion ID
     * @return list of replies as DTOs
     */
    @Override
    public List<ReplyResponseDTO> getRepliesByDiscussionId(Integer discussionId) {
        // get all replies for this discussion
        List<Reply> replies = replyRepository.findByDiscussionId(discussionId);

        // Convert each Reply to DTO and return
        return replies.stream()
            .map(replyMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets all replies by a specific user.
     *
     * @param authorId the author/user ID
     * @return list of replies as DTOs
     */
    @Override
    public List<ReplyResponseDTO> getRepliesByAuthorId(long authorId) {
        // get all replies for this Author (user)
        List<Reply> replies = replyRepository.findByAuthorId(authorId);

        // Convert each Reply ro DTO and return
        return  replies.stream()
            .map(replyMapper::toDTO)
            .collect(Collectors.toList());
    }


    /**
     * Gets the total count of replies for a discussion.
     *
     * @param discussionId the discussion ID
     * @return number of replies
     */
    @Override
    public Long getReplyCount(Integer discussionId) {
        return replyRepository.countByDiscussionId(discussionId);
    }

    // ==== UPDATE ====

    /**
     * Updates an existing reply.
     *
     * @param id the reply ID
     * @param dto the updated reply data
     * @return the updated reply as DTO
     */
    @Override
    public ReplyResponseDTO updateReply(Long id, ReplyRequestDTO dto) {
        // get reply by id
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found with id: " + id));

        // update the content
        reply.setContent(dto.getContent());

        // save updated reply
        Reply updatedReply = replyRepository.update(reply);

        // convert to DTO and return
        return replyMapper.toDTO(updatedReply);
    }

    // ==== DELETE ====

    /**
     *
     * Deletes the reply by specific ID
     *
     * @param id the reply ID
     */
    @Override
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}
