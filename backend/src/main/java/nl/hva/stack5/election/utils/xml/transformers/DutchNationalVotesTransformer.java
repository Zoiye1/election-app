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

        if (aggregated) {
            String partyName = electionData.get(REGISTERED_NAME);
            String votesStr = electionData.get(VALID_VOTES);

            if (partyName != null && votesStr != null) {
                try {
                    long votes = Long.parseLong(votesStr);

                    Party party = election.getParties().get(partyName);

                    // check if party result exists
                    for (PartyResult pr : election.getPartyResults()) {
                        if (pr.getParty().getRegisteredName().equals(partyName)) {
                                return;
                        }
                    }

                    PartyResult partyResult = new PartyResult(election, party, votes);
                    election.getPartyResults().add(partyResult);
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
            String firstName = electionData.get(FIRST_NAME);
            String lastName = electionData.get(LAST_NAME);

            // skip vote registration if candidates shortCode is not in list
            if (!election.getCandidates().containsKey(shortCode)) {
                return;
            }

            String nationalCandidateVotes = electionData.get(VALID_VOTES);
            Candidate candidate = election.getCandidates().get(shortCode);
            Party party = election.getParties().get(this.currentParty);

            if (party != null) {
                CandidateResult candidateResult = new CandidateResult(election, party, candidate, nationalCandidateVotes);
                election.getCandidateResults().add(candidateResult);
            }


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
