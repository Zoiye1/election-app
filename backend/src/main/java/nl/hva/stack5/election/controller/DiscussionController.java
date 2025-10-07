package nl.hva.stack5.election.controller;

import nl.hva.stack5.election.model.Discussion;
import nl.hva.stack5.election.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/discussion")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    public List<Discussion> getAllDiscussions() {
        return discussionService.getAllDiscussions();
    }

    @GetMapping(value = "/{discussionId}")
    public Discussion getDiscussion(@PathVariable Integer discussionId) {
        Optional<Discussion> discussion = discussionService.findById(discussionId);
        if (discussion.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not found");
        return discussion.get();
    }

    @PostMapping
    public Discussion createDiscussion(@RequestBody Discussion discussion) {
        return discussionService.createDiscussion(discussion);
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