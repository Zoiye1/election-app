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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;
import nl.hva.stack5.election.utils.JwtUtil;
import nl.hva.stack5.election.repository.UserRepository;
import nl.hva.stack5.election.model.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/discussion")
@CrossOrigin(origins = "http://localhost:5173")
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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscussionResponseDTO createDiscussion(
            @RequestHeader("Authorization") String authHeader,  // ← ADD THIS
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

    @PutMapping(value = "/{discussionId}")
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

    @DeleteMapping(value = "/{discussionId}")
    public void deleteDiscussion(@PathVariable Integer discussionId) {
        Optional<Discussion> discussion = discussionService.findById(discussionId);
        if (discussion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        discussionService.deleteDiscussion(discussionId);
    }
}