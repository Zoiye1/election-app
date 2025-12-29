package Service;

import jakarta.persistence.EntityNotFoundException;
import nl.hva.stack5.election.dto.PartyDetailResponseDTO;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;
import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;
import nl.hva.stack5.election.repository.PartyRepository;
import nl.hva.stack5.election.service.DutchElectionService;
import nl.hva.stack5.election.service.NationalPartyResultService;
import nl.hva.stack5.election.service.NationalPartyResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * Unit tests for NationalPartyResultService.
 * Tests business logic in isolation using Mockito mocks.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class NationalPartyResultServiceTest {

    // Mocks
    private NationalPartyResultRepository nationalPartyResultRepository;
    private CandidateResultRepository candidateResultRepository;
    private DutchElectionService dutchElectionService;
    private PartyRepository partyRepository;
    // Service being tested
    private NationalPartyResultService nationalPartyResultService;

    @BeforeEach
    void setUp() {
        // Create mocks
        nationalPartyResultRepository = mock(NationalPartyResultRepository.class);
        candidateResultRepository = mock(CandidateResultRepository.class);
        dutchElectionService = mock(DutchElectionService.class);
        partyRepository = mock(PartyRepository.class);

        // Create service with constructor
        nationalPartyResultService = new NationalPartyResultServiceImpl(nationalPartyResultRepository);

        // Inject other dependencies via reflection
        setField(nationalPartyResultService, "candidateResultRepository", candidateResultRepository);
        setField(nationalPartyResultService, "electionService", dutchElectionService);
        setField(nationalPartyResultService, "partyRepository", partyRepository);
    }


    /**
     * HAPPY PATH
     * Tests getTopPartiesByYear with existing data.
     */
    @Test
    void getTopPartiesByYear_ShouldReturnParties_WhenDataExists() {
        // Arrange
        String electionId = "TK2023";
        int limit = 20;
        List<PartyResult> mockResults = createMockPartyResults();

        when(nationalPartyResultRepository.findTopByElectionYear(electionId, limit))
                .thenReturn(mockResults);

        // Act
        List<TopNationalPartiesResponseDTO> result =
                nationalPartyResultService.getTopPartiesByYear(electionId, limit);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("VVD", result.get(0).getPartyName());

        // Verify
        verify(nationalPartyResultRepository, times(1))
                .findTopByElectionYear(electionId, limit);
    }



    /**
     * UNHAPPY PATH
     * Tests getTopPartiesByYear when no data exists.
     */
    @Test
    void getTopPartiesByYear_ShouldReturnEmptyList_WhenDataDoesNotExist() {
        // Arrange
        String electionId = "TK2023";
        int limit = 20;

        when(nationalPartyResultRepository.findTopByElectionYear(electionId, limit))
                .thenReturn(Collections.emptyList());

        // Act
        List<TopNationalPartiesResponseDTO> result =
                nationalPartyResultService.getTopPartiesByYear(electionId, limit);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify
        verify(nationalPartyResultRepository, times(1))
                .findTopByElectionYear(electionId, limit);
    }


    /**
     * HAPPY PATH
     * Tests getPartyDetails with existing data.
     */
    @Test
    void getPartyDetails_ShouldReturnDetails_WhenDataExists() {
        // Arrange
        String electionId = "TK2023";
        long partyId = 4L;

        Party mockParty = new Party("VVD");
        PartyResult mockPartyResult = createMockPartyResult();
        List<CandidateResult> mockCandidates = createMockCandidateResults();
        Election mockElection = createMockElection();

        when(partyRepository.findById(partyId)).thenReturn(mockParty);
        when(nationalPartyResultRepository.findByElectionAndParty(electionId, partyId))
                .thenReturn(mockPartyResult);
        when(candidateResultRepository.findByPartyAndElection(electionId, partyId))
                .thenReturn(mockCandidates);
        when(dutchElectionService.readResults(electionId)).thenReturn(mockElection);

        // Act
        PartyDetailResponseDTO result = nationalPartyResultService.getPartyDetails(electionId, partyId);

        // Assert
        assertNotNull(result);
        assertEquals("VVD", result.getPartyName());
        assertEquals(2000000L, result.getTotalVotes());
        assertEquals(2, result.getCandidates().size());

        // Verify
        verify(partyRepository, times(1)).findById(partyId);
        verify(nationalPartyResultRepository, times(1)).findByElectionAndParty(electionId, partyId);
        verify(candidateResultRepository, times(1)).findByPartyAndElection(electionId, partyId);
        verify(dutchElectionService, times(1)).readResults(electionId);
    }

    /**
     * UNHAPPY PATH
     * Tests getPartyDetails when party does not exist.
     */
    @Test
    void getPartyDetails_ShouldThrowException_WhenPartyNotFound() {
        //Arrange
        String electionId = "TK2023";
        long partyId = 999L;

        when(partyRepository.findById(partyId))
                .thenThrow(new EntityNotFoundException("Party not found"));

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            nationalPartyResultService.getPartyDetails(electionId, partyId);
        });

        // Verify
        verify(partyRepository, times(1)).findById(partyId);
    }

    /**
     * UNHAPPY PATH
     * Tests getPartyDetails when party has no results in election.
     */
    @Test
    void getPartyDetails_ShouldThrowException_WhenNoResultsInElection() {
        // Arrange
        String electionId = "TK2023";
        long partyId = 4L;

        Party mockParty = new Party("VVD");

        when(partyRepository.findById(partyId)).thenReturn(mockParty);
        when(nationalPartyResultRepository.findByElectionAndParty(electionId, partyId))
                .thenReturn(null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            nationalPartyResultService.getPartyDetails(electionId, partyId);
        });

        // Verify
        verify(partyRepository, times(1)).findById(partyId);
        verify(nationalPartyResultRepository, times(1)).findByElectionAndParty(electionId, partyId);
    }

    /**
     * Creates mock PartyResult entities for testing.
     */
    private List<PartyResult> createMockPartyResults() {
        Election election = new Election("TK2023");

        Party party1 = new Party("VVD");
        Party party2 = new Party("PVV");

        PartyResult result1 = new PartyResult(election, party1, 2000000L);
        PartyResult result2 = new PartyResult(election, party2, 2500000L);

        return Arrays.asList(result1, result2);
    }

    /**
     * Creates a mock PartyResult for testing.
     */
    private PartyResult createMockPartyResult() {
        Election election = new Election("TK2023");
        election.setTotalCounted(10000000L);

        Party party = new Party("VVD");

        return new PartyResult(election, party, 2000000L);
    }

    /**
     * Creates mock CandidateResult entities for testing.
     */
    private List<CandidateResult> createMockCandidateResults() {
        Election election = new Election("TK2023");
        Party party = new Party("VVD");

        Candidate candidate1 = new Candidate("Mark", "Rutte", "RutteM");
        Candidate candidate2 = new Candidate("Dilan", "Yeşilgöz", "YesD");

        CandidateResult result1 = new CandidateResult(election, party, candidate1, "500000");
        CandidateResult result2 = new CandidateResult(election, party, candidate2, "300000");

        return Arrays.asList(result1, result2);
    }

    /**
     * Creates a mock Election with totalCounted for testing.
     */
    private Election createMockElection() {
        Election election = new Election("TK2023");
        election.setTotalCounted(10000000L);
        return election;
    }

    /**
     * Helper method to set private fields using reflection.
     * Used to inject mock services during testing.
     *
     * @param target the object containing the field
     * @param fieldName the name of the private field
     * @param value the value to set
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
