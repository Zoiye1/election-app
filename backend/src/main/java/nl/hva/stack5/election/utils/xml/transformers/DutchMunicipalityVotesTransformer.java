package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.utils.xml.VotesTransformer;

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
//        System.out.printf("%s party votes: %s\n", aggregated ? "Municipality" : "Polling station", electionData);

        String partyName = electionData.get("RegisteredName");

        Party party = election.getParties().get(partyName);

        if (party == null) {
            System.out.printf("Party %s not found in election %s. Skipping...\n", partyName, election.getId());
            return;
        }

        String constantName = electionData.get("ConstantName");
        String validVotes = electionData.get("ValidVotes");

        Municipality municipality = election.getMunicipalities().computeIfAbsent(constantName, Municipality::new);

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
//        System.out.printf("%s candidate votes: %s\n", aggregated ? "Municipality" : "Polling station", electionData);
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
//        System.out.printf("%s meta data: %s\n", aggregated ? "Municipality" : "Polling station", electionData);
    }

}
