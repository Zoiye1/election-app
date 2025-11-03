package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.ConstituencyVotes;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.model.PartyConstituencyResults;
import nl.hva.stack5.election.utils.xml.VotesTransformer;

import java.util.Map;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchConstituencyVotesTransformer implements VotesTransformer {
    private final Election election;

    public DutchConstituencyVotesTransformer(Election election) {
        this.election = election;
    }

    @Override
    // this function returns a party with the total amount of votes of a Constituency
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {
        Party party = new Party(electionData.get("RegisteredName"));
        ConstituencyVotes constituencyVotes = new ConstituencyVotes(electionData.get("ContestName"), electionData.get("ValidVotes"));
        // fix(send right election)
        PartyConstituencyResults partyConstituencyResults = new PartyConstituencyResults(party, constituencyVotes, election);
        election.getPartyConstituencyResults().add(partyConstituencyResults);
    }

    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s candidate votes: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s meta data: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }
}
