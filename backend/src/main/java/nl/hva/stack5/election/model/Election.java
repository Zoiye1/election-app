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
    private long totalCounted;

    //Lists and maps containing info that can be retrieved.
    private List<PartyConstituencyResults> constituencyResults = new ArrayList<>();
    private Map<String, PartyResult> partyResults = new HashMap<>();

    //list of parties with Identifier and names of the parties.
    private Map<String, Party> parties = new HashMap<>();

    //List of all elected candidates
    private HashMap<String, Candidate> candidates = new HashMap<>();
    private HashMap<String, CandidateResult> candidateResults = new HashMap<>();

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

    public Map<String, Party> getParties() {return this.parties;}

    public Map<String, PartyResult> getPartyResults(){
        return partyResults;
    }

    public HashMap<String, Candidate> getCandidates(){
        return candidates;
    }

    public List<PartyConstituencyResults> getPartyConstituencyResults() {
     return constituencyResults;
    }

    public long getTotalCounted() {
        return totalCounted;
    }


    public void setTotalCounted(long totalCounted) {
        this.totalCounted = totalCounted;
    }

    public HashMap<String, CandidateResult> getCandidateResults() {return candidateResults;}

    public void setCandidateResults(HashMap<String, CandidateResult> candidateResults) {}
}
