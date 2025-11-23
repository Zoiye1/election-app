package nl.hva.stack5.election.api;

import nl.hva.stack5.election.model.ConstituencyPartyVotes;
import nl.hva.stack5.election.model.Election;
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

    public ElectionController(DutchElectionService electionService) {
        this.electionService = electionService;
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
            folderName = electionId;
        }
        return electionService.importResults(electionId, folderName);
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
    public List<ConstituencyPartyVotes> getConstituencyPartyVotes(@PathVariable String electionId) {
        Election election = electionService.readResults(electionId);
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        return election.getConstituencyPartyVotes();
    }


    @GetMapping("{electionId}/municipalities/{municipalityName}")
    public List<MunicipalityPartyVotes> getMunicipalityPartyVotes(@PathVariable String electionId) {
        Election election = electionService.readResults(electionId);
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");

        }
        return election.getMunicipalityPartyVotes();
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
}
