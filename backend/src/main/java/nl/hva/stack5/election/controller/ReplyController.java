package nl.hva.stack5.election.controller;


import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.model.User;
import nl.hva.stack5.election.repository.ReplyRepository;
import nl.hva.stack5.election.repository.UserRepository;
import nl.hva.stack5.election.service.ReplyService;
import nl.hva.stack5.election.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private ReplyRepository replyRepostory;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new reply for a discussion.
     *
     * @param dto the reply data
     * @return the created reply with HTTP 201
     */
    @PostMapping
    public ResponseEntity<ReplyResponseDTO> createReply
    (@RequestHeader("Authorization") String authHeader ,
     @RequestBody ReplyRequestDTO dto) {

        // Remove "Bearer " so only the token is left
        String token = authHeader.replace("Bearer ", "");

        // get user email from token
        String email = jwtUtil.extractUsername(token);

        // search user by email in database
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        // sets id of logged in user
        dto.setUserId(user.getId());

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
    public ResponseEntity<Void> deleteReply
    (@RequestHeader("Authorization") String authHeader ,
     @PathVariable Long id) {

        // Remove "Bearer " so only the token is left
        String token = authHeader.replace("Bearer ", "");

        // get user email from token
        String email = jwtUtil.extractUsername(token);

        // search user by email in database
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        // retrieve the reply by id
        Reply reply = replyRepostory.findById(id)
                        .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found"));

        // check if author is the currently logged-in user.
        if (!reply.getAuthor().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User can only delete their own replies!");
        }

        //Delete the reply.
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }

}
