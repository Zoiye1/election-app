package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    // Read
    Optional<Candidate> getCandidateById(int candidateId);
    Optional<Candidate> getCandidateByShortCode(String shortCode);
    List<Candidate> getAllCandidates();
}
