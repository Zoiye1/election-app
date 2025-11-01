package nl.hva.stack5.election.model;

import jakarta.servlet.http.Part;


//TODO: make this class a Entity to be ready to migrate to database

/**
 * CandidateResult represents the voting results for a specific candidate
 * in a particular election, including their party affiliation and the
 * total number of votes received at the national level.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class CandidateResult {

    // Instance variables
    private Election election;
    private Party party;
    private final Candidate candidate;
    private String nationalCandidateVotes;

    /**
     * Constructs a CandidateResult with the specified election, party, candidate, and vote count.
     *
     * @param election the election in which the candidate participated
     * @param party the political party the candidate represents
     * @param candidate the candidate who received votes
     * @param nationalCandidateVotes the total number of votes the candidate received at the national level
     */
    public CandidateResult(Election election, Party party,  Candidate candidate, String nationalCandidateVotes) {
        this.election = election;
        this.party = party;
        this.candidate = candidate;
        this.nationalCandidateVotes = nationalCandidateVotes;

    }

    // Getters
    public Party getParty() {return this.party;}

    public Candidate getCandidate() {return candidate;}

    public String getNationalCandidateVotes() {
        return nationalCandidateVotes;
    }


    // Setters
    public void setElection(Election election) {this.election = election;}

    public void setParty(Party party) {this.party = party;}

    public void setNationalCandidateVotes(String nationalCandidateVotes) {
        this.nationalCandidateVotes = nationalCandidateVotes;
    }



}
