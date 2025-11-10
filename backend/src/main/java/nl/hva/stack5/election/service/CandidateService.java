package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Candidate;

public interface CandidateService {

    // Create
    Candidate createCandidate(Candidate candidate);
    Candidate createMultipleCandidate(Candidate candidate);
}
