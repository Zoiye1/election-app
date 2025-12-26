package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.CandidateResult;

import java.util.List;

/**
 * Repository interface for managing {@link CandidateResult} entities in the database.
 * <p>
 * Provides methods for:
 * <ul>
 * <li>Finding top candidates by election year</li>
 * <li>Retrieving candidate results with vote counts</li>
 * <li>Sorting results by number of votes</li>
 * </ul>
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface CandidateResultRepository {

    /**
     * Finds the top candidates by number of votes for a specific election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top candidate results ordered by votes descending
     */
    List<CandidateResult> findTopByElectionYear(String electionId, int limit);


    /**
     * Finds all candidates for a specific party in a specific election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param partyId the party identifier
     * @return List of top candidate results ordered by votes descending
     */
    List<CandidateResult> findByPartyAndElection(String electionId, long partyId);
}