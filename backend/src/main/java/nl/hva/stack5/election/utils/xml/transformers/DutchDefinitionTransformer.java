package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.utils.xml.DefinitionTransformer;
import nl.hva.stack5.election.utils.xml.TagAndAttributeNames;

import java.util.Map;

import static nl.hva.stack5.election.utils.xml.TagAndAttributeNames.*;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchDefinitionTransformer implements DefinitionTransformer {
    private final Election election;

    public DutchDefinitionTransformer(Election election) {
        this.election = election;
    }
    
    @Override
    public void registerRegion(Map<String, String> electionData) {
        System.out.println("Committee: " + electionData);
    }

    @Override
    public void registerParty(Map<String, String> electionData) {
        String partyName = electionData.get(REGISTERED_APPELLATION);

        // Only add if not already loaded from database
        if (!election.getParties().containsKey(partyName)) {
            Party party = new Party(partyName);
            election.getParties().put(partyName, party);
            System.out.printf("Added new party: %s\n", partyName);
        } else {
            System.out.printf("Using existing party: %s\n", partyName);
        }
    }
}
