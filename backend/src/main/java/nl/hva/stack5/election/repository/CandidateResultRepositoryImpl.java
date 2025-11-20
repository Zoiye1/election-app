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
public class CandidateResultRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds the top candidates by number of votes for a specific election.
     * Results are ordered by vote count in descending order.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit the maximum number of results to return
     * @return List of top candidate results ordered by votes descending
     */
    @Override
    public List<CandidateResult> findByTopElectionYear (String electionId, int limit) {
        TypedQuery<CandidateResult> query = entityManager.createQuery(
                "SELECT cr FROM CandidateResult cr " +
                        "WHERE cr.election.id = :electionId " +
                        "ORDER BY CAST(cr.nationalCandidateVotes AS long) DESC",
                CandidateResult.class
        );
    }
}
