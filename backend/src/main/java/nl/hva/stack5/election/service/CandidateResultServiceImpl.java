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

    @Override
    public List<TopCandidateResponseDTO> getTopCandidatesByYear(String electionId, int limit) {
        List<CandidateResult> results = candidateResultRepository.findTopByElectionYear(electionId, limit);

        return results.stream()
                .map(CandidateResultsMapper::toDTO)
                .toList();
    }
}
