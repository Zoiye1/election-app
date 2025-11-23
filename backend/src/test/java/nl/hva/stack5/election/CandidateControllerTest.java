package nl.hva.stack5.election;

import nl.hva.stack5.election.controller.CandidateController;
import nl.hva.stack5.election.dto.TopCandidateResponseDTO;
import nl.hva.stack5.election.service.CandidateResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void getTopCandidates_ShouldReturnTopCandidates_whenDataExists() {
        //Arrange
        String electionId = "TK2023";
        List<TopCandidateResponseDTO> mockCandidates = createMockCandidates();

        when(candidateResultService.getTopCandidatesByElection(electionId, 20))
                .thenReturn(mockCandidates);

    }

    /**
     * Creates mock candidate DTOs for testing.
     *
     * @return list of mock TopCandidateResponseDTO objects
     */
    private List<TopCandidateResponseDTO> createMockCandidates() {
        TopCandidateResponseDTO candidate1 = new TopCandidateResponseDTO(
                1L, "Geert Wilders", "PVV", 500000L
        );
        TopCandidateResponseDTO candidate2 = new TopCandidateResponseDTO(
                2L, "Mark Rutte", "VVD", 450000L
        );
        return Arrays.asList(candidate1, candidate2);
    }
}

