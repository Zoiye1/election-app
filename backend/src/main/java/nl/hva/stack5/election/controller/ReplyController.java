package nl.hva.stack5.election.controller;


import nl.hva.stack5.election.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST Controller for Reply operations.
 * Handles HTTP requests for managing replies.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
}
