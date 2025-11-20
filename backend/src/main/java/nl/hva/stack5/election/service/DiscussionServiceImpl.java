package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.DiscussionMapper;
import nl.hva.stack5.election.dto.DiscussionRequestDTO;
import nl.hva.stack5.election.dto.DiscussionResponseDTO;
import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.repository.DiscussionRepository;
import nl.hva.stack5.election.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     * @return List of all discussions ordered by most recent
     */

    @Override
    public List<Discussion> getAllDiscussions(DiscussionResponseDTO responseDTO) {
        return discussionRepository.findAllByOrderByCreatedAtDesc();
    }
    /**
     * @param id
     * @return Discussion if exists
     */
    @Override
    public Optional<Discussion> findById(int id) {
        return discussionRepository.findById(id);
    }

    /**
     * @param requestDTO
     * @return Discussion if successfully created
     */
    @Override
    public Discussion createDiscussion(DiscussionRequestDTO requestDTO) {
        Discussion discussion = discussionMapper.toEntity(requestDTO);

        User author = userRepository.findById(requestDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + requestDTO.getAuthorId()));
        discussion.setAuthor(author);

        return discussionRepository.save(discussion);

    }

    /**
     * @param discussion
     * @return Discussion if successfully updated
     */
    @Override
    public Discussion updateDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    /**
     * @param id
     */
    @Override
    public void deleteDiscussion(int id) {
        discussionRepository.deleteById(id);
    }

}