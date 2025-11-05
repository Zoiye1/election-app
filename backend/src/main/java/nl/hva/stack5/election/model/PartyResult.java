package nl.hva.stack5.election.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * PartyResult represents the voting results for a political party
 * in a specific election, including the total number of votes received
 * at the national level.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Entity
@Table( name = "party_results")
public class PartyResult {
    // Instance variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "election_id")
    @JsonBackReference
    private Election election;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "party_id")
    private Party party;

    @Column( nullable = false)
    private long votes;

    // Default constructor for JPA
    public PartyResult() {}
    /**
     * Constructs a PartyResult with the specified election, party, and vote count.
     *
     * @param election the election in which the party participated
     * @param party the political party
     * @param votes the total number of votes the party received nationally
     */
   public PartyResult( Election election, Party party, long votes) {

        this.election = election;
        this.party = party;
        this.votes = votes;
    }



    // Getters
    public Election getElection() {return election;}

    public long getVotes() {
        return votes;
    }

    public Party getParty() {
        return party;
    }


    // Setters
    public void setVotes(long votes) {
        this.votes = votes;
    }

    public void setParty(Party party) {this.party = party;}

    public void setElection(Election election) {this.election = election;}
}
