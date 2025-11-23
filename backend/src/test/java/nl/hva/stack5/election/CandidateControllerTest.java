package nl.hva.stack5.election;

import nl.hva.stack5.election.controller.CandidateController;
import nl.hva.stack5.election.service.CandidateResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;

/**
 * Unit tests for CandidateController.
 * Tests REST API endpoints in isolation using MockMvc and mockito.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class CandidateControllerTest {
    // this simulates http requests
    private MockMvc mockMvc;

    //mocks the service(as it is a unit test)
    @Mock
    private CandidateResultService candidateResultService;

    @InjectMocks
    private CandidateController candidateController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
    }
}

