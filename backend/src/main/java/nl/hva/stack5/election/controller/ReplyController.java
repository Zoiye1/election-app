package nl.hva.stack5.election.controller;


import nl.hva.stack5.election.dto.ReplyRequestDTO;
import nl.hva.stack5.election.dto.ReplyResponseDTO;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
    public ResponseEntity<ReplyResponseDTO> createReply (@RequestBody ReplyRequestDTO dto) {
        ReplyResponseDTO createdReply = replyService.createReply(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
