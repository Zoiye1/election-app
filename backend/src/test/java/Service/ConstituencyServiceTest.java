package Service;

import jakarta.persistence.EntityNotFoundException;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.service.ConstituencyService;
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
class ConstituencyServiceTest {

    @Mock
    private ElectionRepository electionRepository;

    @Mock
    private ConstituencyRepository constituencyRepository;

    @InjectMocks
    private DutchElectionService dutchElectionService;

    @InjectMocks
    private ConstituencyService constituencyService;

//  TESTS FOR THE METHOD getResultsByConstituency
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
                constituencyService.getResultsByConstituency("TK2023", "Amsterdam");

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

        Throwable exception =  assertThrows(EntityNotFoundException.class, () -> constituencyService.getResultsByConstituency("TK2023", "Amsterdam"));
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
                constituencyService.getResultsByConstituency("TK2023", "Den Haag");


        // ASSERT
        assertTrue(result.isEmpty());
        assertNotNull(result);
        verify(constituencyRepository)
                .findByConstituencyAndElectionId("TK2023", "Den Haag");
    }

    @Test
    void getResultsByConstituency_NullParameters_Exception() throws EntityNotFoundException {
        // ARRANGE
        when(electionRepository.findById(null)).thenReturn(null);

        // ACT + ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> constituencyService.getResultsByConstituency(null, null)
        );

        assertEquals("election not found", exception.getMessage());

        // VERIFY
        verify(constituencyRepository, never())
                .findByConstituencyAndElectionId(any(), any());
    }

    // TESTS FOR THE METHOD CalculateVotesPercentage

    @Test
    void CalculateVotesPercentage_Expect_Results_toBe_0() throws IllegalAccessException {
        // ARRANGE
        Election election = new Election();
        election.setTotalCounted(1000);

        when(electionRepository.findById("TK2023")).thenReturn(election);

        List<ConstituencyPartyVotesDTO> dummyResults = new ArrayList<>();
        when(constituencyRepository.findByConstituencyAndElectionId("TK2023", "Rotterdam"))
                .thenReturn(dummyResults);

        // ACT
        double result = constituencyService.CalculateVotesPercentage("TK2023", "Rotterdam");

        // ASSERT
        assertEquals(0.0, result); //
        verify(constituencyRepository).findByConstituencyAndElectionId("TK2023", "Rotterdam");
    }

    @Test
    void CalculateVotesPercentage_Returns_RightResults() throws IllegalAccessException {
        // ARRANGE
        Election election = new Election();
        when(electionRepository.findById("TK2023")).thenReturn(election);

        election.setTotalCounted(100);

      List<ConstituencyPartyVotesDTO> constituencyPartyVotesDTO =  new ArrayList<>();
      constituencyPartyVotesDTO.add(new ConstituencyPartyVotesDTO("", "", 25L));

      when(constituencyRepository.findByConstituencyAndElectionId("TK2023", "Amsterdam")).thenReturn(constituencyPartyVotesDTO);


        // ACT

        double results = constituencyService.CalculateVotesPercentage("TK2023", "Amsterdam");

        // ASSERT
        assertEquals(25.0, results);
        verify(constituencyRepository).findByConstituencyAndElectionId("TK2023", "Amsterdam");

    }

    @Test
    void CaluclateVotesPercentage_RightRounding() throws IllegalAccessException {

        // ARRANGE
        Election election = new Election();
        when(electionRepository.findById("TK2023")).thenReturn(election);

        election.setTotalCounted(12558);

        List<ConstituencyPartyVotesDTO> constituencyPartyVotesDTO =  new ArrayList<>();
        constituencyPartyVotesDTO.add(new ConstituencyPartyVotesDTO("", "", 8756L));

        when(constituencyRepository.findByConstituencyAndElectionId("TK2023", "Amsterdam")).thenReturn(constituencyPartyVotesDTO);


        // ACT
        double results = constituencyService.CalculateVotesPercentage("TK2023", "Amsterdam");



        // ASSERT
       assertEquals(69.7, results);
        verify(constituencyRepository).findByConstituencyAndElectionId("TK2023", "Amsterdam");
    }




}




