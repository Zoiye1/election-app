package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.utils.xml.DefinitionTransformer;

import java.util.Map;

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
        System.out.println("Party: " + electionData);
    }
}
