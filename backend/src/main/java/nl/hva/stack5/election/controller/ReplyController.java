package nl.hva.stack5.election.controller;


import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for Reply operations.
 * Handles HTTP requests for managing replies.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * Creates a new reply for a discussion.
     *
     * @param dto the reply data
     * @return the created reply with HTTP 201
     */
    @PostMapping
    public ResponseEntity<ReplyResponseDTO> createReply (@RequestBody ReplyRequestDTO dto) {
        ReplyResponseDTO createdReply = replyService.createReply(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

    /**
     * Gets all replies for a specific discussion.
     *
     * @param discussionId the discussion ID
     * @return list of replies with HTTP 200
     */
    @GetMapping("/discussion/{discussionId}")
    public ResponseEntity<List<ReplyResponseDTO>> getRepliesByDiscussion (@PathVariable Integer discussionId) {
        List<ReplyResponseDTO> replies = replyService.getRepliesByDiscussionId(discussionId);
        return ResponseEntity.ok(replies);
    }

    /**
     * Deletes a reply by its ID.
     *
     * @param id the reply ID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }

}
