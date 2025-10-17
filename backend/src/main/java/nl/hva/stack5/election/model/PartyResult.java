package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.List;

public class PartyResult {

    private final String partyName;
    private String partyId;
    private long votes;
    //private final List<Candidate> candidates;

    /**
     *
     * @param partyName the name of the party
     * @param partyId the unique identifier of the party
     * @param votes the total votes a party received nationally
     */
   public PartyResult(String partyName, String partyId, long votes) {
        this.partyName = partyName;
        this.partyId = partyId;
        this.votes = votes;
//        this.candidates = new ArrayList<>();
    }

//    public void setCandidates(String firstName, String surName, String shortCode) {
//        candidates.add(new Candidate(firstName, surName, shortCode));
//    }

//    public List<Candidate> getCandidates() {
//        return candidates;
//    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public String getPartyName() {
        return partyName;
    }

    public String getPartyId() {
        return partyId;
    }
}
