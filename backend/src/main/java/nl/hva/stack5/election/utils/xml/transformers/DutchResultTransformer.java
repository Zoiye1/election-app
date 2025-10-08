package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.utils.xml.TagAndAttributeNames;
import nl.hva.stack5.election.utils.xml.VotesTransformer;

import java.util.Map;



/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchResultTransformer implements VotesTransformer, TagAndAttributeNames {
    private final Election election;

    public DutchResultTransformer(Election election) {
        this.election = election;
    }

    @Override
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("National party result: %s\n", electionData);
    }

    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {

        String firstName = electionData.get(FIRST_NAME);
        String lastName = electionData.get(LAST_NAME);
        String shortCode = electionData.get(CANDIDATE_IDENTIFIER_SHORT_CODE);

        Candidate candidate = new Candidate ( firstName, lastName, shortCode);

        election.getCandidates().put(shortCode, candidate);
        System.out.printf("National candidate result: %s\n", electionData);
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
