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
public class DutchNationalVotesTransformer implements VotesTransformer, TagAndAttributeNames {
    private final Election election;

    public DutchNationalVotesTransformer(Election election) {
        this.election = election;
    }

    @Override
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s party votes: %s\n", aggregated ? "National" : "Constituency", electionData);
    }

    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
        String shortCode = electionData.get(CANDIDATE_IDENTIFIER_SHORT_CODE);
        String nationalCandidateVotes = electionData.get(VALID_VOTES);

        if (shortCode != null && aggregated) {
            Candidate candidate = election.getCandidates().get(shortCode);

            System.out.println(">>> Processing: " + shortCode + " with votes: " + nationalCandidateVotes);
            if (candidate != null) {
                candidate.setNationalCandidateVotes(nationalCandidateVotes);
                System.out.println("✓ Set votes for " + shortCode + ": " + nationalCandidateVotes);
            }
        } else {
            System.out.printf("WARNING: Candidate Not Found");
        }
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s meta data: %s\n", aggregated ? "National" : "Constituency", electionData);
    }
}
