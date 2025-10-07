package nl.hva.stack5.election.model;

/**
 * This will hold the information for one specific election.<br/>
 * <b>This class is by no means production ready! You need to alter it extensively!</b>
 */
public class Election {
    private final String id;
    private List<PartyConstituencyResults> constituencyResults = new ArrayList<>();
    private List <Candidate> candidates = new ArrayList<>();

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

    public List<Candidate> getCandidates() {
        return candidates;
    }


    public List<PartyConstituencyResults> getPartyConstituencyResults() {
     return constituencyResults;

    }
}
