package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.CandidateResultsMapper;
import nl.hva.stack5.election.dto.TopCandidateResponseDTO;
import nl.hva.stack5.election.model.CandidateResult;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of CandidateResultService.
 * Handles business logic for candidate result operations.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Service
public class CandidateResultServiceImpl implements CandidateResultService {

    @Autowired
    private CandidateResultRepository candidateResultRepository;


    public CandidateResultServiceImpl(CandidateResultRepository candidateResultRepository) {
        this.candidateResultRepository = candidateResultRepository;
    }
    /**
     * Retrieves the top candidates by number of votes for a specific election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of candidates to return
     * @return List of top candidates with their vote counts and party affiliation
     */
    @Override
    public List<TopCandidateResponseDTO> getTopCandidatesByElection(String electionId, int limit) {
        List<CandidateResult> results = candidateResultRepository.findTopByElectionYear(electionId, limit);

        return results.stream()
                .map(CandidateResultsMapper::toDTO)
                .toList();
    }
}
