package nl.hva.stack5.election.model;

import jakarta.servlet.http.Part;

public class CandidateResult {

    private Election election;
    private Candidate candidate;
    private String nationalCandidateVotes;

    public CandidateResult(Election election, Candidate candidate, String nationalCandidateVotes) {
        this.election = election;
        this.candidate = candidate;
        this.nationalCandidateVotes = nationalCandidateVotes;

    }

    public void setElection(Election election) {this.election = election;}

    public Candidate getCandidate() {return candidate;}

    public void setNationalCandidateVotes(String nationalCandidateVotes) {
        this.nationalCandidateVotes = nationalCandidateVotes;
    }

    public String getNationalCandidateVotes() {
        return nationalCandidateVotes;
    }

}
