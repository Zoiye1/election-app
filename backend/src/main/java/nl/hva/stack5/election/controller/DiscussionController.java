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
            @RequestBody DiscussionRequestDTO requestDTO) {
        Discussion created = discussionService.createDiscussion(requestDTO);
        return discussionMapper.toResponseDTO(created);
    }

    @GetMapping(value = "/{discussionId}")
    public Discussion getDiscussion(@PathVariable Integer discussionId) {
        Optional<Discussion> discussion = discussionService.findById(discussionId);
        if (discussion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        return discussion.get();
    }

    @PutMapping(value = "/{discussionId}")
    public Discussion updateDiscussion(@PathVariable Integer discussionId, @RequestBody Discussion discussion) {
        Optional<Discussion> existingDiscussion = discussionService.findById(discussionId);
        if (existingDiscussion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        discussion.setId(discussionId);
        return discussionService.updateDiscussion(discussion);
    }

    @DeleteMapping(value = "/{discussionId}")
    public void deleteDiscussion(@PathVariable Integer discussionId) {
        Optional<Discussion> discussion = discussionService.findById(discussionId);
        if (discussion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        discussionService.deleteDiscussion(discussionId);
    }
}