package nl.hva.stack5.election;


import nl.hva.stack5.election.repository.CandidateRepository;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.service.CandidateResultService;
import nl.hva.stack5.election.service.CandidateResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


/**
 * Unit tests for CandidateResultService.
 * Tests business logic in isolation using Mockito mocks.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class CandidateResultServiceTest {
    //Mocks
    private CandidateResultRepository candidateResultRepository;
    private CandidateResultService candidateResultService;

    @BeforeEach
    public void setUp() {
        candidateResultRepository = mock(CandidateResultRepository.class);
        candidateResultService = new CandidateResultServiceImpl(candidateResultRepository);
    }


    /**
     * getTopCandidatesByElection = the method that is being tested
     * shouldReturnTopCandidates = the expected response
     * whenResultsExist = is the condition under which the expected response should be given.
     */
    @Test
    void getTopCandidatesByElection_shouldReturnTopCandidates_whenResultsExist() {

    }
}
