package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.NationalPartyResultsMapper;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;
import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of NationalPartyResultService.
 * Handles business logic for national party results.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Service
public class NationalPartyResultServiceImpl implements NationalPartyResultService {

    private final NationalPartyResultRepository nationalPartyResultRepository;

    // Constructor
    public NationalPartyResultServiceImpl(NationalPartyResultRepository nationalPartyResultRepository) {
        this.nationalPartyResultRepository = nationalPartyResultRepository;
    }


    @Override
    public List<TopNationalPartiesResponseDTO> getTopPartiesByYear(String electionId, int limit) {
        List<PartyResult> results = nationalPartyResultRepository.findTopByElectionYear(electionId, limit);

        return results.stream()
                .map(NationalPartyResultsMapper::toDTO)
                .toList();
    }
}
