package nl.hva.stack5.election.service;


import nl.hva.stack5.election.dto.PartyDetailResponseDTO;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;
import nl.hva.stack5.election.model.PartyResult;

import java.util.List;

/**
 * Service interface for managing national party results.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface NationalPartyResultService {

    /**
     * Retrieves the top parties by number of votes for a specific election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top party results as DTOs
     */
    List<TopNationalPartiesResponseDTO> getTopPartiesByYear(String electionId, int limit);

    /**
     * Retrieves detailed party information including all candidates.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param partyId the party identifier
     * @return PartyDetailResponseDTO with party stats and candidates
     */
    PartyDetailResponseDTO getPartyDetails(String electionId, long partyId);

    /**
     * Finds a specific party result for an election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param partyId the party identifier
     * @return PartyResult for the specific party, or null if not found
     */
    PartyResult findByElectionAndParty(String electionId, long partyId);
}
