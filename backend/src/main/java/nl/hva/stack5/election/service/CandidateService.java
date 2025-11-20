package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.CandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    // Read
    Optional<Candidate> getCandidateById(int candidateId);
    /**
     *
     * Retrieves all candidates.
     *
     * @return List of all candidates as DTOs
     */
    List<CandidateResponseDTO> getAllCandidates();
}
