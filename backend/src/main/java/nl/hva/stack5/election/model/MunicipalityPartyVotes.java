package nl.hva.stack5.election.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "municipality_party_votes")
public class MunicipalityPartyVotes {
    @Id
    @Column(name = "id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "election_id")
    @JsonBackReference
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "party_id")
    private Party party;

    @Column
    private int votes;

    public MunicipalityPartyVotes() { }

    public MunicipalityPartyVotes(Election election, Municipality municipality, Party party, int votes) {
        this.election = election;
        this.municipality = municipality;
        this.party = party;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public Municipality getMunicipality() {
        return municipality;
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