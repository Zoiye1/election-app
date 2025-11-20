package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.CandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    // Read
    /**
     * Retrieves a candidate by their ID.
     *
     * @param id the candidate ID
     * @return Optional containing the candidate DTO if found
     */
    Optional<CandidateResponseDTO> getCandidateById(long id);

    /**
     *
     * Retrieves a list all candidates.
     *
     * @return List of all candidates as DTOs
     */
    List<CandidateResponseDTO> getAllCandidates();
}
