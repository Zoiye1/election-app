package nl.hva.stack5.election.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "constituency_party_votes")
public class ConstituencyPartyVotes {
    @Id
    @Column(name = "id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "election_id")
    @JsonBackReference
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "party_id")
    private Party party;

    @Column
    private int votes;

    public ConstituencyPartyVotes() { }

    public ConstituencyPartyVotes(Election election, Constituency constituency, Party party, int votes) {
        this.election = election;
        this.constituency = constituency;
        this.party = party;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public Party getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}