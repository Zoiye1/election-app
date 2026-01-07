package nl.hva.stack5.election.controller;

import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import nl.hva.stack5.election.dto.DiscussionMapper;
import nl.hva.stack5.election.dto.DiscussionRequestDTO;
import nl.hva.stack5.election.dto.DiscussionResponseDTO;
import java.util.stream.Collectors;
import nl.hva.stack5.election.utils.JwtUtil;
import nl.hva.stack5.election.repository.UserRepository;
import nl.hva.stack5.election.model.User;

import java.util.List;
import java.util.Optional;

@RestController
 @RequestMapping(value = "v1/discussion")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<DiscussionResponseDTO> getAllDiscussions() {
        List<Discussion> discussions = discussionService.getAllDiscussions(null);
        return discussions.stream()
                .map(discussionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get discussion by ID endpoint
    @GetMapping("{discussionId}")
    public DiscussionResponseDTO getDiscussionById(@PathVariable Integer discussionId) {
        Optional<Discussion> discussion = discussionService.findById(discussionId);
        if (discussion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        }
        return discussionMapper.toResponseDTO(discussion.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscussionResponseDTO createDiscussion(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody DiscussionRequestDTO requestDTO) {

        // Extract token
        String token = authHeader.replace("Bearer ", "");

        // Get user email from token
        String email = jwtUtil.extractUsername(token);

        // Find user in database
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        requestDTO.setAuthorId(user.getId());

        // Create discussion with user
        Discussion created = discussionService.createDiscussion(requestDTO);
        return discussionMapper.toResponseDTO(created);
    }

    @PutMapping(value = "{discussionId}")
    public DiscussionResponseDTO updateDiscussion(
            @PathVariable Integer discussionId,
            @RequestBody DiscussionRequestDTO requestDTO) {

        Optional<Discussion> existingDiscussion = discussionService.findById(discussionId);
        if (existingDiscussion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        }

        // Use the mapper to update the existing entity
        Discussion discussion = existingDiscussion.get();
        discussionMapper.updateEntityFromDTO(requestDTO, discussion);
        discussion.setId(discussionId);

        // Save the updated discussion
        Discussion updated = discussionService.updateDiscussion(discussion);

        // Return as DTO
        return discussionMapper.toResponseDTO(updated);
    }

    // Add authorization check to delete method
    @DeleteMapping(value = "{discussionId}")
    public void deleteDiscussion(
            @PathVariable Integer discussionId,
            @RequestHeader("Authorization") String authHeader) {

        // Extract token
        String token = authHeader.replace("Bearer ", "");

        // Get userId directly from token
        Integer currentUserId = jwtUtil.extractUserId(token);

        if (currentUserId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        // Get discussion and check if it exists
        Optional<Discussion> discussionOpt = discussionService.findById(discussionId);
        if (discussionOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        }

        Discussion discussion = discussionOpt.get();

        // Check if current user is the author
        if (!discussion.getAuthor().getId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "You can only delete your own discussions");
        }

        // Delete the discussion
        discussionService.deleteDiscussion(discussionId);
    }
}