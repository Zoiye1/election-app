package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

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
}
