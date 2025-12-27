package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.*;
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

        // get PartyResult for total party votes and party name
        PartyResult partyResult = nationalPartyResultRepository.findByElectionAndParty(electionId, partyId);

        if (partyResult == null) {
            return null;
        }

        // get total national votes for percentage and seat calculations
        long totalNationalVotes = electionService.readResults(electionId).getTotalCounted();

        // Get party info
        String partyName = partyResult.getParty().getRegisteredName();
        long totalPartyVotes = partyResult.getVotes();

        // Calculate national percentage
        double nationalPercentage = (totalPartyVotes / (double) totalNationalVotes) * 100;

        // Calculate seats using electoral quota(look up online!)
        int seats = (int) (totalPartyVotes / (totalNationalVotes / 150));

        List<PartyCandidateResponseDTO> candidates = candidateResults.stream()
                .map(cr -> {
                    long votes = Long.parseLong(cr.getNationalCandidateVotes());
                    double partyPercentage = (votes / (double) totalPartyVotes) * 100;
                    return new PartyCandidateResponseDTO (
                    cr.getCandidate().getId(),
                            cr.getCandidate().getFirstName() + " " + cr.getCandidate().getSurname(),
                            votes,
                            partyPercentage
                    );
                })
                .toList();

        return new PartyDetailResponseDTO (
                partyName,
                totalPartyVotes,
                nationalPercentage,
                seats,
                null,
                candidates
        );

    }



}
