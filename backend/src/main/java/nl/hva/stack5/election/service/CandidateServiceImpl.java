package nl.hva.stack5.election.service;


import nl.hva.stack5.election.dto.CandidateMapper;
import nl.hva.stack5.election.dto.CandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> getCandidateById(int candidateId) {
        return candidateRepository.findById(candidateId);
    }


    /**
     * Retrieves all candidates.
     *
     * @return List of all candidates as DTOs
     */
    @Override
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(CandidateMapper::toDTO)
                .toList();
    }

}
