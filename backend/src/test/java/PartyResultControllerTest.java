import nl.hva.stack5.election.controller.PartyResultController;
import nl.hva.stack5.election.dto.PartyCandidateResponseDTO;
import nl.hva.stack5.election.dto.PartyDetailResponseDTO;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;
import nl.hva.stack5.election.service.NationalPartyResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * Unit tests for PartyResultController.
 * Tests controller logic in isolation using Mockito.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class PartyResultControllerTest {

    @Mock
    private NationalPartyResultService nationalPartyResultService;

    private PartyResultController partyResultController;

    @BeforeEach
    void setUp() {
        partyResultController = new PartyResultController();
        setField(partyResultController, "nationalPartyResultService", nationalPartyResultService);
    }


    /**
     * Helper method to set private fields using reflection.
     * Used to inject mock services into controllers during testing.
     *
     * @param target the object containing the field
     * @param fieldName the name of the private field
     * @param value the value to set
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            // find field by name
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            // makes field accessible
            field.setAccessible(true);
            // set the value of the field
            field.set(target, value);
        } catch (Exception e) {
            // throw error if field is not accessible for example
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates mock party DTOs for testing.
     */
    private List<TopNationalPartiesResponseDTO> createMockParties() {

        TopNationalPartiesResponseDTO party1 = new TopNationalPartiesResponseDTO (
                4L, "VVD", 22556677L
        );

        TopNationalPartiesResponseDTO party2 = new TopNationalPartiesResponseDTO (
                10L, "PVV", 77889900L
        );

        return Arrays.asList(party1, party2);

    }

    /**
     * creates mock PartyCandidate DTO's for testing.
     */
    private PartyDetailResponseDTO createMockPartyDetail() {
        PartyCandidateResponseDTO candidate1 = new PartyCandidateResponseDTO (
                4L, "Mark Rutte", 45000L, 10
        );

        PartyCandidateResponseDTO candidate2 = new PartyCandidateResponseDTO (
                5L, "Dilan Yeşilgöz", 55000L, 20
        );

        List<PartyCandidateResponseDTO> candidatesVVD = Arrays.asList(candidate1, candidate2);

        return new PartyDetailResponseDTO(
                "VVD",
                80000L,
                15.5,
                24,
                2.3,
                candidatesVVD
        );

    }
}
