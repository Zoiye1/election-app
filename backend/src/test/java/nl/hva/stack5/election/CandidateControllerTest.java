package nl.hva.stack5.election;

import nl.hva.stack5.election.controller.CandidateController;
import nl.hva.stack5.election.service.CandidateResultService;
import nl.hva.stack5.election.service.CandidateService;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
}