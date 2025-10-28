package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.utils.xml.DefinitionTransformer;
import nl.hva.stack5.election.utils.xml.TagAndAttributeNames;

import java.util.Map;

import static nl.hva.stack5.election.utils.xml.TagAndAttributeNames.AFFILIATION_IDENTIFIER_ID;
import static nl.hva.stack5.election.utils.xml.TagAndAttributeNames.REGISTERED_NAME;

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
        String partyId = electionData.get(AFFILIATION_IDENTIFIER_ID);
        String partyName = electionData.get(REGISTERED_NAME);

        boolean exists = false;
        for (Party p : election.getParties()) {
            if (partyId != null && partyId.equals(p.getPartyId())) {
                exists = true;
                break;
            }
        }

        // Alleen toevoegen als nog niet bestaat
        if (!exists) {
            Party party = new Party(partyId, partyName);
            election.getParties().add(party);
            System.out.printf("Added party: %s\n", partyId);
        }

        System.out.println("Party: " + electionData);
    }
}
