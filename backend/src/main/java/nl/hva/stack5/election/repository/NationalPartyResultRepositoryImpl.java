package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.PartyResult;
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
public class NationalPartyResultRepositoryImpl implements NationalPartyResultRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public NationalPartyResultRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds the top Parties by number of votes for a specific election.
     * Results are ordered by vote count in descending order.
     *
     * @param electionId the election identifier (e.g., "TK2023")
     * @param limit      the maximum number of results to return
     * @return List of top Party results ordered by votes descending
     */
    @Override
    public List<PartyResult> findTopByElectionYear(String electionId, int limit) {
        TypedQuery<PartyResult> query = entityManager.createQuery(
                "SELECT pr FROM PartyResult pr " +
                        "WHERE pr.election.id = :electionId " +
                        "ORDER BY CAST(pr.votes AS long) DESC",
                PartyResult.class
        );

        query.setParameter("electionId", electionId);
        query.setMaxResults(limit);

        return query.getResultList();

    }

}
