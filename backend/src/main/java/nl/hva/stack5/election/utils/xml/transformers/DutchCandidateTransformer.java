package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.utils.xml.CandidateTransformer;
import nl.hva.stack5.election.utils.xml.TagAndAttributeNames;

import java.util.Map;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchCandidateTransformer implements CandidateTransformer, TagAndAttributeNames {
    private final Election election;

    public DutchCandidateTransformer(Election election) {
        this.election = election;
    }

    @Override
    public void registerCandidate(Map<String, String> electionData) {
        Candidate candidate = new Candidate (
                electionData.get("FirstName"),
                electionData.get("LastName"),
                electionData.get("CandidateIdentifier-Id") );
        election.getCandidates().add(candidate);
        System.out.println("Registering candidate: " + electionData);
    }


}
