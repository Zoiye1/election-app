package nl.hva.stack5.election;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.CandidateResult;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.repository.CandidateResultRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void findTopCandidatesByCandidate_shouldReturnTopCandidates_whenResultsExist()
    {
        // Arrange
        String electionId = "TK2023";
        int limit = 10;

        TypedQuery<CandidateResult> mockQuery = mock(TypedQuery.class);

        List<CandidateResult> mockResults = createMockCandidateResults();

        // Act
        when(entityManager.createQuery(anyString(), eq(CandidateResult.class)))
                        .thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.setMaxResults(anyInt())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(mockResults);

        // Assert
    }

    private List<CandidateResult> createMockCandidateResults() {
        Candidate candidate = new Candidate("Geert", "Wilders", "WildersG");
        Party party = new Party("PVV");
        Election election = new Election("TK2023");
        CandidateResult result = new CandidateResult(election, party, candidate, "500000");
        return List.of(result);
    }

}
;