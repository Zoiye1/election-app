package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.TopCandidateResponseDTO;

import java.util.List;

/**
 * Service interface for managing CandidateResult business logic.
 * Provides methods for retrieving top candidates by election year.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface CandidateResultService {


    /**
     *
     * Retrieves the top N voted candidates by the selected election.
     *
     * @param electionId identifier for the election.
     * @param limit the maximum amount of top candidates that needs to be returned.
     * @return List of top candidates with their vote counts and party affiliation
     */
    List<TopCandidateResponseDTO> getTopCandidatesByYear(String electionId, int limit);
}
