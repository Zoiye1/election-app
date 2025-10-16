package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.PartyResult;
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
        // Alleen nationale (aggregated) party votes verwerken
        if (aggregated) {
        String partyId = electionData.get(AFFILIATION_IDENTIFIER_ID);
            String partyName = electionData.get(REGISTERED_NAME);
            String votesStr = electionData.get(VALID_VOTES);

            if (partyId != null && partyName != null && votesStr != null) {
                try {
                    long votes = Long.parseLong(votesStr);

                    // Nieuwe PartyResult aanmaken en toevoegen aan de lijst
                    PartyResult partyResult = new PartyResult(partyName, partyId, votes);
                    election.getPartyResults().add(partyResult);
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: votes is not the correct format");
                }
            }
        }
    }

    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
        if (aggregated) {
            String shortCode = electionData.get(CANDIDATE_IDENTIFIER_SHORT_CODE);
            String nationalCandidateVotes = electionData.get(VALID_VOTES);

            if (shortCode != null && nationalCandidateVotes != null) {
                boolean found = false;
                for (Candidate candidate : election.getCandidates()) {
                    if (shortCode != null && shortCode.equals(candidate.getShortCode())) {
                        candidate.setNationalCandidateVotes(nationalCandidateVotes);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("NOT FOUND: '" + shortCode + "'");
                    // Print eerste paar candidates om te vergelijken
                    for (int i = 0; i < Math.min(5, election.getCandidates().size()); i++) {
                        System.out.println("  Candidate " + i + ": '" + election.getCandidates().get(i).getShortCode() + "'");
                    }
                }
            }
        }
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s meta data: %s\n", aggregated ? "National" : "Constituency", electionData);
    }
}
