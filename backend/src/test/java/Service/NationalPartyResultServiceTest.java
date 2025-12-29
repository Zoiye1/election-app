package Service;

import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.repository.CandidateResultRepository;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;
import nl.hva.stack5.election.repository.PartyRepository;
import nl.hva.stack5.election.service.DutchElectionService;
import nl.hva.stack5.election.service.NationalPartyResultService;
import nl.hva.stack5.election.service.NationalPartyResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
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
