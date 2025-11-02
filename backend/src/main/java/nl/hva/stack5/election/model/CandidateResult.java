package nl.hva.stack5.election.model;

import jakarta.persistence.*;


//TODO: make this class a Entity to be ready to migrate to database

/**
 * CandidateResult represents the voting results for a specific candidate
 * in a particular election, including their party affiliation and the
 * total number of votes received at the national level.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Entity
@Table(name = "Candidate_results")
public class CandidateResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Instance variables
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "election_id", nullable = false)
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "party_id", nullable = false)
    private Party party;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(nullable = false)
    private String nationalCandidateVotes;


    public CandidateResult() {}

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

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setNationalCandidateVotes(String nationalCandidateVotes) {
        this.nationalCandidateVotes = nationalCandidateVotes;
    }



}
