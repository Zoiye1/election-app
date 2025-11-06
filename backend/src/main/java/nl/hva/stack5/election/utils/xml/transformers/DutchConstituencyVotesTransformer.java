package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Constituency;
import nl.hva.stack5.election.model.ConstituencyPartyVotes;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.utils.xml.VotesTransformer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
@Component
public class DutchConstituencyVotesTransformer implements VotesTransformer {
    private final Election election;

    public DutchConstituencyVotesTransformer(Election election) {
        this.election = election;
    }

    @Override
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {
        // variable with the party name
        String partyName = electionData.get("RegisteredName");

        // checks if party is already registered and saves it if not
        Party party = election.getParties().get(partyName);

        if (party == null) {
            System.out.printf("Party %s not found in election %s. Skipping...\n", partyName, election.getId());
            return;
        }

        // new instance of constituency votes with the name and the amount of votes
        String constantName = electionData.get("ContestName");
        String validVotes = electionData.get("ValidVotes");

        Constituency constituency = election.getConstituencies().computeIfAbsent(constantName, Constituency::new);

        ConstituencyPartyVotes constituencyVotes = new ConstituencyPartyVotes(
                election,
                constituency,
                party,
                Integer.parseInt(validVotes)
        );
        election.getConstituencyPartyVotes().add(constituencyVotes);
    }


    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
        //System.out.printf("%s candidate votes: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        //System.out.printf("%s meta data: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }
}