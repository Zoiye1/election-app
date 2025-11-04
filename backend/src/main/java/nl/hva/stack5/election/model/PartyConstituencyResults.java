package nl.hva.stack5.election.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
/**
 * this Entity holds the amount of votes a party has in a constituency
 */

@Entity
@Table(name = "party_constituency_results")
public class PartyConstituencyResults {

    @Id
    @Column(name = "id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "election_id")
    @JsonBackReference
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituencyvotes_id")
    private ConstituencyVotes constituencyVotes;

    public PartyConstituencyResults() {}



    /**
     *
     * @param party is the party class and holds the party name
     * @param constituencyVotes is the constituencyVotes class ands holds the amount of votes
     * and the name of the constituency
     */
    public PartyConstituencyResults(Party party, ConstituencyVotes constituencyVotes,  Election election) {
        this.election = election;
        this.party = party;
        this.constituencyVotes = constituencyVotes;
    }

    public Long getId() {
        return id;
    }

    public Party getParty() {
        return party;
    }
    public void setParty(Party party) {
        this.party = party;
    }
    public ConstituencyVotes getConstituencyVotes() {
        return constituencyVotes;
    }
    public void setConstituencyVotes(ConstituencyVotes constituencyVotes) {
        this.constituencyVotes = constituencyVotes;
    }
    public Election getElection() {
        return election;
    }
    public void setElection(Election election) {
        this.election = election;
    }
}
