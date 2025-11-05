package nl.hva.stack5.election.utils.xml.transformers;

import nl.hva.stack5.election.model.ConstituencyVotes;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.model.PartyConstituencyResults;
import nl.hva.stack5.election.repository.ConstituencyVotesRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.repository.PartyConstituencyResultsRepository;
import nl.hva.stack5.election.repository.PartyRepository;
import nl.hva.stack5.election.service.ElectionService;
import nl.hva.stack5.election.service.PartyService;
import nl.hva.stack5.election.utils.xml.VotesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Just prints to content of electionData to the standard output.>br/>
 * <b>This class needs heavy modification!</b>
 */
@Component
public class DutchConstituencyVotesTransformer implements VotesTransformer {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PartyConstituencyResultsRepository partyConstituencyResultsRepository;

    @Autowired
    private ElectionRepository electionRepository;




    private Election election;

    public DutchConstituencyVotesTransformer(Election election) {
        this.election = election;
    }

    // sets the election
    public void setElection(Election election) {
        this.election = election;
    }


    @Override

     // Function registers the party votes and the constituency
    public void registerPartyVotes(boolean aggregated, Map<String, String> electionData) {

        // variable with the party name
        String partyName = electionData.get("RegisteredName");

        // checks if party is already registered and saves it if not
        Party party = partyRepository.findByRegisteredName(partyName);
        if (party == null){
           party = new Party(partyName);
           partyRepository.save(party);
        }
        // new instance of constituency votes with the name and the amount of votes

        ConstituencyVotes constituencyVotes = new ConstituencyVotes(electionData.get("ContestName"), electionData.get("ValidVotes"));

        // result is an instance of partyConstituency result
        PartyConstituencyResults result = new PartyConstituencyResults(party, constituencyVotes, election);

        // saves result in the database
        partyConstituencyResultsRepository.save(result);
        // adds results to the list
        election.getPartyConstituencyResults().add(result);
        // sets the given election in PartyConstituencyResults
        result.setElection(election);

    }


    @Override
    public void registerCandidateVotes(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s candidate votes: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }

    @Override
    public void registerMetadata(boolean aggregated, Map<String, String> electionData) {
        System.out.printf("%s meta data: %s\n", aggregated ? "Constituency" : "Municipality", electionData);
    }
}
