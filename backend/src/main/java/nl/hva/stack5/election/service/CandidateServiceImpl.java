package nl.hva.stack5.election.service;


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

    @Override
    public Optional<Candidate> getCandidateByShortCode(String shortCode) {
        return candidateRepository.findByShortCode(shortCode);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

}
