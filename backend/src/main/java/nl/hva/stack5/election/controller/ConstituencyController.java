package nl.hva.stack5.election.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.service.DutchElectionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import nl.hva.stack5.election.service.ConstituencyService;


import java.util.List;

@RestController
@RequestMapping("elections")
public class ConstituencyController {

    private final ConstituencyService constituencyService;
    private final DutchElectionService electionService;

    public ConstituencyController(ConstituencyService constituencyService,  DutchElectionService electionService) {
        this.constituencyService = constituencyService;
        this.electionService = electionService;
    }

    /**
     *
     * @param electionId holds the identifier for an election "TK2023"
     * @param constituencyName Holds the name of the selected constituency
     * @return a list of the parties with their name and total votes in that constituency
     * @throws ResponseStatusException when the election does not exist
     */
    @Operation(
            summary = "Retrieve constituency results",
            description = "Returns all parties and their votes for a given constituency."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Election not found")
    })
    @GetMapping("{electionId}/{constituencyName}")
    public List<ConstituencyPartyVotesDTO> getConstituencyPartyVotes(@PathVariable String electionId, @PathVariable String constituencyName) throws ResponseStatusException {

        return constituencyService.getResultsByConstituency(electionId, constituencyName);
    }


    /**
     *
     * @param electionId holds the identifier for an election
     * @param constituencyName holds the name of an election
     * @return the percentage of the total votes for a constituency compared to the total national votes
     */
    @Operation(
            summary = "Retrieve votes percentage for a constituency",
            description = "Returns the percentage of votes that a given constituency contributes to the total national votes for the specified election."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved vote percentage"),
            @ApiResponse(responseCode = "404", description = "Election or constituency not found")
    })
    @GetMapping("{electionId}/{constituencyName}/votes-percentage")
    public Double getVotesPercentage(@PathVariable String electionId, @PathVariable String constituencyName) throws ResponseStatusException {


        return constituencyService.CalculateVotesPercentage(electionId, constituencyName);

    }

    @GetMapping("{electionId}/{constituencyName}/totalVotes")
    public long getTotalVotes(@PathVariable String electionId, @PathVariable String constituencyName) throws ResponseStatusException {

        return constituencyService.getTotalConstituencyVotes(electionId, constituencyName);

    }
}
