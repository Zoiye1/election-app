package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This will hold the information for one specific election.<br/>
 * <b>This class is by no means production ready! You need to alter it extensively!</b>
 */
public class Election {
    private final String id;

    //Lists and maps containing info that can be retrieved.
    private List<PartyConstituencyResults> constituencyResults = new ArrayList<>();
    private Map<String, Candidate> candidates = new HashMap();

    public Election(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "You have to create a proper election model yourself!";
    }

    public String getId() {
        return id;
    }

    public Map <String, Candidate> getCandidates () {
        return candidates;
    }


    public List<PartyConstituencyResults> getPartyConstituencyResults() {
     return constituencyResults;
    }
}
