package nl.hva.stack5.election.controller;

import nl.hva.stack5.election.dto.CandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.service.CandidateResultService;
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
@RequestMapping(value = "/candidates")
@CrossOrigin(origins = "http://localhost:5173")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateResultService candidateResultService;

    /**
     * Retrieves all candidates from the database.
     *
     * @return List of all candidates
     */
    @GetMapping
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }


    /**
     * Retrieves a specific candidate by their ID.
     *
     * @param id the unique identifier of the candidate
     * @return ResponseEntity containing the candidate if found, 404 Not Found otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable long id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

