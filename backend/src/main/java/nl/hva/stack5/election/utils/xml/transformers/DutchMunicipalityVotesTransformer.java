package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.utils.xml.VotesTransformer;
import nl.hva.stack5.election.model.Municipality;
import nl.hva.stack5.election.model.MunicipalityPartyVotes;


import java.util.Map;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
public class DutchMunicipalityVotesTransformer implements VotesTransformer {
    private final Election election;

    public DutchMunicipalityVotesTransformer(Election election) {this.election = election;}

    @Override
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {

//        This code skips polling station level data and only processes aggregated municipality level data
        if (!aggregated) {
            return;
        }


        String partyName = electionData.get("RegisteredName");

        Party party = election.getParties().get(partyName);

        if (party == null) {
            System.out.printf("Party %s not found in election %s. Skipping...\n", partyName, election.getId());
            return;
        }

        String AuthorityIdentifier = electionData.get("AuthorityIdentifier");
        String validVotes = electionData.get("ValidVotes");

        Municipality municipality = election.getMunicipalities().computeIfAbsent(AuthorityIdentifier, Municipality::new);
        MunicipalityPartyVotes municipalityVotes = new MunicipalityPartyVotes(
                election,
                municipality,
                party,
                Integer.parseInt(validVotes)
        );
        election.getMunicipalityPartyVotes().add(municipalityVotes);


    }

    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
    }

}
