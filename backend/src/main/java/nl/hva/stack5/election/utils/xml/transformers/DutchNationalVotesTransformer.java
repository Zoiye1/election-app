package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.utils.xml.TagAndAttributeNames;
import nl.hva.stack5.election.utils.xml.VotesTransformer;

import java.util.Map;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchNationalVotesTransformer implements VotesTransformer, TagAndAttributeNames {
    private final Election election;
    private String currentParty;

    public DutchNationalVotesTransformer(Election election) {
        this.election = election;
    }

    @Override
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {
        // Alleen nationale (aggregated) party votes verwerken
        if (aggregated) {
            String partyName = electionData.get(REGISTERED_NAME);
            String votesStr = electionData.get(VALID_VOTES);

            if (partyName != null && votesStr != null) {
                try {
                    long votes = Long.parseLong(votesStr);

                    Party party = election.getParties().get(partyName);

                    // Nieuwe PartyResult aanmaken en toevoegen aan de lijst
                    PartyResult partyResult = new PartyResult(election, party, votes);
                    election.getPartyResults().put(partyName, partyResult);
                    this.currentParty = partyName;
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

            Candidate candidate = election.getCandidates().get(shortCode);
            Party party = election.getParties().get(this.currentParty);

            if (candidate == null) {
                System.err.println("ERROR: candidate does not exist.");
                return;
            }
            if (party == null) {
                System.err.println("ERROR: party does not exist.");
                return;
            }

            CandidateResult candidateResult = new CandidateResult(election, party, candidate, nationalCandidateVotes);
            election.getCandidateResults().put(shortCode, candidateResult);
        }
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        if (aggregated) {
            String totalCounted = electionData.get(TOTAL_COUNTED);

            if (totalCounted != null) {
                try {
                    long count = Long.parseLong(totalCounted);
                    election.setTotalCounted(count);
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: totalCounted is not the correct format");
                }
            }
        }
    }
}
