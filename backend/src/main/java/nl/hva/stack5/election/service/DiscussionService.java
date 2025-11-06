package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.DiscussionRequestDTO;
import nl.hva.stack5.election.dto.DiscussionResponseDTO;
import nl.hva.stack5.election.model.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {
    List<Discussion> getAllDiscussions(DiscussionResponseDTO responseDTO);
    Optional<Discussion> findById(int id);
    Discussion createDiscussion(DiscussionRequestDTO requestDTO);
    Discussion updateDiscussion(Discussion discussion);
    void deleteDiscussion(int id);
}