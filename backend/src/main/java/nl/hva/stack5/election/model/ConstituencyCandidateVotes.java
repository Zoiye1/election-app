package nl.hva.stack5.election.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * ConstituencyCandidateVotes represents the voting results for a specific candidate
 * in a particular constituency within an election.
 *
 * This entity stores the relationship between a candidate, constituency, and their
 * vote count in that specific geographic area.
 *
 * @author Generated for Stack5 Election Project
 * @version 1.0
 */
@Entity
@Table(name = "constituency_candidate_votes")
public class ConstituencyCandidateVotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "election_id", nullable = false)
    @JsonBackReference
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituency_id", nullable = false)
    private Constituency constituency;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(nullable = false)
    private int votes;

    // Default constructor for JPA
    public ConstituencyCandidateVotes() {}

    /**
     * Constructs a ConstituencyCandidateVotes with the specified election, constituency,
     * candidate, party, and vote count.
     *
     * @param election the election in which the votes were cast
     * @param constituency the constituency where the votes were recorded
     * @param candidate the candidate who received the votes
     * @param party the political party the candidate represents
     * @param votes the number of votes the candidate received in this constituency
     */
    public ConstituencyCandidateVotes(Election election, Constituency constituency,
                                      Candidate candidate, Party party, int votes) {
        this.election = election;
        this.constituency = constituency;
        this.candidate = candidate;
        this.party = party;
        this.votes = votes;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Election getElection() {
        return election;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public Party getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }

    // Setters
    public void setElection(Election election) {
        this.election = election;
    }

    public void setConstituency(Constituency constituency) {
        this.constituency = constituency;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}