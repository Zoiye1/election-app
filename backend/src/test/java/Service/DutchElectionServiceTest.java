package Service;

import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.service.DutchElectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests the dutchElectionService with all it's
 * important function (Happyflow and Unhappyflow)
 */
@ExtendWith(MockitoExtension.class)
class DutchElectionServiceTest {

    @Mock
    private ElectionRepository electionRepository;

    @Mock
    private ConstituencyRepository constituencyRepository;

    @InjectMocks
    private DutchElectionService dutchElectionService;

    @Test
    void getResultsByConstituency_existingElection_returnsResults() throws Exception {
        // ARRANGE
        Election election = new Election("TK2023");
        List<ConstituencyPartyVotesDTO> dummyResults = List.of(
                new ConstituencyPartyVotesDTO("VVD", "Amsterdam", 12345L)
        );

        when(electionRepository.findById("TK2023")).thenReturn(election);
        when(constituencyRepository.findByConstituencyAndElectionId("TK2023", "Amsterdam"))
                .thenReturn(dummyResults);

        // ACT
        List<ConstituencyPartyVotesDTO> result =
                dutchElectionService.getResultsByConstituency("TK2023", "Amsterdam");

        // ASSERT
        assertEquals(1, result.size());
        assertEquals("VVD", result.get(0).getPartyName());
        verify(electionRepository).findById("TK2023");
        verify(constituencyRepository).findByConstituencyAndElectionId("TK2023", "Amsterdam");
    }


    @Test
    void getResultsByConstituency_missingElection_throwsException() {
        // ARRANGE
        when(electionRepository.findById("TK2023")).thenReturn(null);

        // ACT
        electionRepository.findById("TK2023");

        // ASSERT

        Throwable exception =  assertThrows(IllegalArgumentException.class, () -> dutchElectionService.getResultsByConstituency("TK2023", "Amsterdam"));
        assertEquals("election not found",  exception.getMessage());


        verify(electionRepository).findById("TK2023");
        verify(constituencyRepository, never()).findByConstituencyAndElectionId(any(), any());
    }

    @Test
    void getResultsByConstituency_ConstituencyDoesntExist_ReturnEmptyArray() throws IllegalAccessException {
        // ARRANGE
        Election election = new Election();
        when(electionRepository.findById("TK2023")).thenReturn(election);

        List<ConstituencyPartyVotesDTO> dummyResults = new ArrayList<>();
        when(constituencyRepository.findByConstituencyAndElectionId("TK2023", "Den Haag")).thenReturn(dummyResults);



        // ACT
        List<ConstituencyPartyVotesDTO> result =
                dutchElectionService.getResultsByConstituency("TK2023", "Den Haag");


        // ASSERT
        assertTrue(result.isEmpty());
        assertNotNull(result);
        verify(constituencyRepository)
                .findByConstituencyAndElectionId("TK2023", "Den Haag");
    }

    @Test
    void getResultsByConstituency_NullParameters_Exception() throws IllegalAccessException {
        // ARRANGE
        when(electionRepository.findById(null)).thenReturn(null);

        // ACT + ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dutchElectionService.getResultsByConstituency(null, null)
        );

        assertEquals("election not found", exception.getMessage());

        // VERIFY
        verify(constituencyRepository, never())
                .findByConstituencyAndElectionId(any(), any());
    }

}
