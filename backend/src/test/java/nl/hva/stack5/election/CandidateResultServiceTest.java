package nl.hva.stack5.election;


import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.CandidateResult;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.repository.CandidateRepository;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.service.CandidateResultService;
import nl.hva.stack5.election.service.CandidateResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
        //Arrange
        String electionId = "TK2023";
        int limit = 10;
        //Mock repo results
        List<CandidateResult> mockResults = createMockCandidateResults();

        // when method is called upon, repo has to return mockresults.
        when(candidateResultRepository.findTopByElectionYear(electionId, limit))
                .thenReturn(mockResults);
        //Act

        //Assert
    }

    private List<CandidateResult> createMockCandidateResults() {

        Candidate candidate1 = new Candidate("Geert", "Wilders", "WildersG");

        Party party1 = new Party("PVV");

        Election election1 = new Election("TK2023");


        //makes candidate result out of mock data above
        CandidateResult result1 = new CandidateResult(election1, party1, candidate1, "500000");


        //returns a list of the candidateresult we made above
        return List.of(result1);
    }
}
