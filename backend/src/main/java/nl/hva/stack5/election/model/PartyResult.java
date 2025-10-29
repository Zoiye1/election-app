package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.List;

public class PartyResult {

    private Election election;
    private Party party;
    private long votes;


    //private final List<Candidate> candidates;

    /**
     *
     * @param party the entity of a party
     * @param votes the total votes a party received nationally
     */
   public PartyResult( Election election, Party party, long votes) {

        this.election = election;
        this.party = party;
        this.votes = votes;
    }


    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public void setParty(Party party) {this.party = party;}

    public Party getParty() {
        return party;
    }

    public void setElection(Election election) {this.election = election;}
}
