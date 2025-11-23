package nl.hva.stack5.election;

import nl.hva.stack5.election.controller.CandidateController;
import nl.hva.stack5.election.service.CandidateResultService;
import nl.hva.stack5.election.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.util.ReflectionTestUtils.setField;


/**
 * Unit tests for CandidateController.
 * Tests controller logic in isolation using Mockito.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class CandidateControllerTest {

    @Mock
    private CandidateResultService candidateResultService;

    @Mock
    private CandidateService candidateService;

    private CandidateController candidateController;

    @BeforeEach
    void setUp() {
        candidateController = new CandidateController();
        setField(candidateController, "candidateResultService", candidateResultService);
        setField(candidateController, "candidateService", candidateService);
    }


}