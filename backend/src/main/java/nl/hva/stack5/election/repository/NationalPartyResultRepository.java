package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.PartyResult;

import java.util.List;

/**
 * Repository interface for managing {@link PartyResult} entities in the database.
 * <p>
 * Provides methods for:
 * <ul>
 * <li>Finding top Parties by election year</li>
 * <li>Retrieving party results with vote counts</li>
 * <li>Sorting results by number of votes</li>
 * </ul>
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface NationalPartyResultRepository {

    /**
     * Finds the top candidates by number of votes for a specific election.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top Party results ordered by votes descending
     */
    List<PartyResult> findTopByElectionYear(String electionId, int limit);
}
