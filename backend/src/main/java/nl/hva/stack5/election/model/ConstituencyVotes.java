package nl.hva.stack5.election.model;

/**
 * This class holds the name of the constituency and the amount of votes of that constituency
 */
public class ConstituencyVotes {
    private String constituencyName;
    private String Votes;

    /**
     *
     * @param constituencyName hols the name
     * @param Votes holds the amount of votes
     */
        public ConstituencyVotes(String constituencyName, String Votes) {
        this.constituencyName = constituencyName;
        this.Votes = Votes;
    }

    public String getConstituencyName() {
        return constituencyName;
    }
    public String getVotes() {
            return Votes;
    }
}
