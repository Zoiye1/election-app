package nl.hva.stack5.election.service;

import jakarta.persistence.TypedQuery;
import nl.hva.stack5.election.dto.NationalPartyResultsMapper;
import nl.hva.stack5.election.dto.PartyDetailResponseDTO;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;
import nl.hva.stack5.election.model.CandidateResult;
import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of NationalPartyResultService.
 * Handles business logic for national party results.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Service
public class NationalPartyResultServiceImpl implements NationalPartyResultService {

    @Autowired
    private NationalPartyResultRepository nationalPartyResultRepository;

    @Autowired
    private CandidateResultRepository candidateResultRepository;

    @Autowired
    private DutchElectionService electionService;

    // Constructor
    public NationalPartyResultServiceImpl(NationalPartyResultRepository nationalPartyResultRepository) {
        this.nationalPartyResultRepository = nationalPartyResultRepository;
    }

    /**
     * Retrieves the top parties by number of votes for a specific election.
     * Fetches party results from repository and converts them to DTOs.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top party results as DTOs ordered by votes descending
     */
    @Override
    public List<TopNationalPartiesResponseDTO> getTopPartiesByYear(String electionId, int limit) {
        // Fetches top parties from repository
        List<PartyResult> results = nationalPartyResultRepository.findTopByElectionYear(electionId, limit);

        // Convert entities to DTOs
        return results.stream()
                .map(NationalPartyResultsMapper::toDTO)
                .toList();
    }

    /**
     * Retrieves detailed party information including all candidates.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param partyId the party identifier
     * @return PartyDetailResponseDTO with party stats and candidates
     */
    @Override
    public PartyDetailResponseDTO getPartyDetails(String electionId, long partyId) {
        // Get all candidates for this party
        List<CandidateResult> candidateResults = candidateResultRepository.findByPartyAndElection(electionId, partyId);

        if (candidateResults.isEmpty()) {
            return null;
        }
    }



}
