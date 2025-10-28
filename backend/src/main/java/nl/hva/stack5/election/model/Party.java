package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Party class holds the name of a part and the amount votes a party has
 */

public class Party {

    private String partyName;
    private String partyId;
    /**
     *
     * @param partyName contains the party name
     */

    public Party(String partyName,  String partyId) {
        this.partyId = partyId;
        this.partyName = partyName;
    }

    // returns the part name
    public String getPartyName() {
        return partyName;
    }
    public String getPartyId() {return partyId;}

}
