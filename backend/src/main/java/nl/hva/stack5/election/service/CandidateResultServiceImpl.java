package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.TopCandidateResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateResultServiceImpl implements CandidateResultService {

    @Override
    public List<TopCandidateResponseDTO> getTopCandidatesByYear(String electionId, int limit) {
        return List.of();
    }
}
