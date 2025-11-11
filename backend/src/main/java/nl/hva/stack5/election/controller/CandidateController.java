package nl.hva.stack5.election.controller;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for managing Candidate endpoints.
 * Provides endpoints to retrieve candidate information.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@RestController
@RequestMapping(value = "candidates")
@CrossOrigin(origins = "http://localhost:5173")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable int id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

