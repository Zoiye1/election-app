package nl.hva.stack5.election.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.CandidateResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of CandidateResultRepository.
 * Handles database operations for candidate election results.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Repository
@Transactional
public class CandidateResultRepositoryImpl implements CandidateResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CandidateResultRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds the top candidates by number of votes for a specific election.
     * Results are ordered by vote count in descending order.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top candidate results ordered by votes descending
     */
    @Override
    public List<CandidateResult> findTopByElectionYear (String electionId, int limit) {
        TypedQuery<CandidateResult> query = entityManager.createQuery(
                "SELECT cr FROM CandidateResult cr " +
                        "WHERE cr.election.id = :electionId " +
                        "ORDER BY CAST(cr.nationalCandidateVotes AS long) DESC",
                CandidateResult.class
        );

        query.setParameter("electionId", electionId);
        query.setMaxResults(limit);

        return query.getResultList();

    }

    /**
     * Finds all candidates for a specific party in a specific election.
     * Results are ordered by vote count in descending order.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param partyId the party identifier
     * @return List of candidate results ordered by votes descending
     */
    @Override
    public List<CandidateResult> findByPartyAndElection(String electionId, long partyId) {
        TypedQuery<CandidateResult> query = entityManager.createQuery(
                "SELECT cr FROM CandidateResult cr " + // Select all candidate results
                        "WHERE cr.election.id = :electionId " + // Filter by election year
                        "AND cr.party.id = :partyId " + // Filter by party
                        "ORDER BY CAST(cr.nationalCandidateVotes AS long) DESC", // Sort by votes, highest first
                CandidateResult.class
        );

        query.setParameter("electionId", electionId);
        query.setParameter("partyId", partyId);

        return query.getResultList();
    }
}
