package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Party class holds the name of a part and the amount votes a party has
 */

public class Party {

    private String partyName;

    /**
     *
     * @param partyName contains the party name
     */

    public Party(String partyName) {
        this.partyName = partyName;
    }

    // returns the part name
    public String getPartyName() {
        return partyName;
    }

}
