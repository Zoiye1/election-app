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
    }e

}
