package nl.hva.stack5.election.model;

import java.util.HashMap;

/**
 * Party class holds the name of a part and the amount votes a party has
 */

public class Party {

    private String partyName;
    private String partyVotes;

    /**
     *
     * @param partyName contains the party name
     * @param partyVotes contains the party votes
     */

    public Party(String partyName, String partyVotes) {
        this.partyName = partyName;
        this.partyVotes = partyVotes;
    }

    // returns the part name
    public String getPartyName() {
        return partyName;
    }

    // returns the party votes
    public String getPartVotes() {
        return partyVotes;
    }
}
