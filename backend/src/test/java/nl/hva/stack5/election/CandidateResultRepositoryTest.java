package nl.hva.stack5.election;


import jakarta.persistence.EntityManager;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.repository.CandidateResultRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

/**
 * Unit tests for CandidateResultRepository.
 * Tests database query logic in isolation using Mockito.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
class CandidateResultRepositoryTest {
    private EntityManager entityManager;
    private CandidateResultRepository candidateResultRepository;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        candidateResultRepository = new CandidateResultRepositoryImpl(entityManager);
    }
}
