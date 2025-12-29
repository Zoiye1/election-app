import nl.hva.stack5.election.controller.PartyResultController;
import nl.hva.stack5.election.service.NationalPartyResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
