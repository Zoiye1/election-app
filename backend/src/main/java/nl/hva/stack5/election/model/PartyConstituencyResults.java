package nl.hva.stack5.election.model;

/**
 * this class holds the amount of votes a party has in a constituency
 */
public class PartyConstituencyResults {
    private Party party;
    private ConstituencyVotes constituencyVotes;

    /**
     *
     * @param party is the party class and holds the party name
     * @param constituencyVotes is the constituencyVotes class ands holds the amount of votes
     * and the name of the constituency
     */
    public PartyConstituencyResults(Party party, ConstituencyVotes constituencyVotes) {
        this.party = party;
        this.constituencyVotes = constituencyVotes;
    }

    public Party getParty() {
        return party;
    }

    public ConstituencyVotes getConstituencyVotes() {
        return constituencyVotes;
    }
}
