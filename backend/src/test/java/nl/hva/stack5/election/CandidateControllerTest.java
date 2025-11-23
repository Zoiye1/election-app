package nl.hva.stack5.election;

import nl.hva.stack5.election.controller.CandidateController;
import nl.hva.stack5.election.dto.TopCandidateResponseDTO;
import nl.hva.stack5.election.service.CandidateResultService;
import nl.hva.stack5.election.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

    /**
     * Tests getTopCandidates with existing data.
     * Verifies correct service call and response.
     */
    @Test
    void getTopCandidates_shouldReturnCandidates_whenDataExists() {
        // Arrange
        String electionId = "TK2023";
        List<TopCandidateResponseDTO> mockCandidates = createMockCandidates();

        when(candidateResultService.getTopCandidatesByElection(electionId, 20))
                .thenReturn(mockCandidates);

        // Act
        ResponseEntity<List<TopCandidateResponseDTO>> response =
                candidateController.getTopCandidates(electionId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Geert Wilders", response.getBody().get(0).getFullName());
        assertEquals("PVV", response.getBody().get(0).getParty());

        // Verify
        verify(candidateResultService, times(1))
                .getTopCandidatesByElection(electionId, 20);
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