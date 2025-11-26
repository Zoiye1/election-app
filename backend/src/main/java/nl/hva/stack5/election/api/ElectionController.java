package nl.hva.stack5.election.api;

import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.service.ConstituencyService;
import nl.hva.stack5.election.model.MunicipalityPartyVotes;
import nl.hva.stack5.election.service.DutchElectionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Demo controller for showing how you could load the election data in the backend.
 */
@RestController
@RequestMapping("elections")
public class ElectionController {
    private final DutchElectionService electionService;
    private final ConstituencyService constituencyService;

    public ElectionController(DutchElectionService electionService, ConstituencyService constituencyService) {
        this.electionService = electionService;
        this.constituencyService = constituencyService;
    }

    /**
     * Processes the result for a specific election.
     * @param electionId the id of the election, e.g. the value of the Id attribute from the ElectionIdentifier tag.
     * @param folderName the name of the folder that contains the XML result files. If none is provided the value from
     *                   the electionId is used.
     * @return Election if the results have been processed successfully. Please be sure yoy don't output all the data!
     * Just the general data about the election should be sent back to the front-end!<br/>
     * <i>If you want to return something else please feel free to do so!</i>
     */
    @PostMapping("{electionId}")
    public Election importResults(@PathVariable String electionId, @RequestParam(required = false) String folderName) {
        if (folderName == null) {
            System.out.println("No folder name provided");
            return electionService.importResults(electionId, folderName);
        } else {
            System.out.println(" folder name provided");
            return electionService.importResults(electionId, folderName);
        }
    }

    @GetMapping("{electionId}")
    public Election getElection(@PathVariable String electionId) {
        Election election = electionService.readResults(electionId);
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }
        return election;
    }

    @GetMapping("{electionId}/{constituencyName}")
    public List<ConstituencyPartyVotesDTO> getConstituencyPartyVotes(@PathVariable String electionId, @PathVariable String constituencyName) throws IllegalAccessException {
        return constituencyService.getResultsByConstituency(electionId, constituencyName);
    }


    @GetMapping("{electionId}/municipalities/{municipalityName}")
    public List<MunicipalityPartyVotes> getMunicipalityVotes(
            @PathVariable String electionId,
            @PathVariable String municipalityName) {

        Election election = electionService.readResults(electionId);
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election " + electionId + " not found");
        }

        return election.getMunicipalityPartyVotes().stream()
                .filter(vote -> vote.getMunicipality().getName().equalsIgnoreCase(municipalityName))
                .toList();
    }
    /**
     * Retrieves total number of votes for a specific election
     *
     * @param electionId the identifier for the election ( for example "TK2023" )
     * @return the total number of votes counted in the election
     * @throws ResponseStatusException http 404 if election is not found
     */
    @GetMapping("{electionId}/total-votes")
    public long getTotalVotes(@PathVariable String electionId) {

        // retrieve election from database
        Election election = electionService.readResults(electionId);

        //TODO: Instead of response status use response entity.
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        return election.getTotalCounted();

    }

    /**
     *
     * @param electionId holds the identifier for an election
     * @param constituencyName holds the name of an election
     * @return the percentage of the total votes for a constituency compared to the total national votes
     */

    @GetMapping("{electionId}/{constituencyName}/votes-percentage")
    public Double getVotesPercentage(@PathVariable String electionId, @PathVariable String constituencyName) throws IllegalAccessException {

        // retrieve election from database
        Election election = electionService.readResults(electionId);

        //TODO: Instead of response status use response entity.
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        return constituencyService.CalculateVotesPercentage(electionId, constituencyName);

    }
}
