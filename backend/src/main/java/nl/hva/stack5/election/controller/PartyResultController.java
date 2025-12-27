package nl.hva.stack5.election.controller;

//TODO: create better api handling with logging etc.

import jakarta.validation.constraints.NotBlank;
import nl.hva.stack5.election.dto.PartyDetailResponseDTO;
import nl.hva.stack5.election.dto.TopNationalPartiesResponseDTO;

import nl.hva.stack5.election.service.NationalPartyResultService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for national party results.
 * Provides endpoints for retrieving top parties by election.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/v1/parties")
@CrossOrigin(origins = "http://localhost:5173")
public class PartyResultController {

    @Autowired
    private NationalPartyResultService nationalPartyResultService;

    @GetMapping("/top")
    public ResponseEntity<List<TopNationalPartiesResponseDTO>> getTopParties(
            @RequestParam @NotBlank(message = "ElectionId cannot be empty") String electionId ) {
        // Retrieve top parties
        List<TopNationalPartiesResponseDTO> parties = nationalPartyResultService.getTopPartiesByYear(electionId, 20);

        // Return results with OK status
        return ResponseEntity.ok(parties);
    }

    /**
     * Retrieves detailed information for a specific party including all candidates.
     *
     * @param partyId the unique identifier of the party
     * @param electionId the election identifier (e.g., "TK2023")
     * @return ResponseEntity containing party details with candidates, votes, and statistics
     */
    @GetMapping("/{partyId}/details")
    public ResponseEntity<PartyDetailResponseDTO> getPartyDetails(
            @PathVariable long partyId,
            @RequestParam @NotBlank(message = "ElectionId cannot be empty") String electionId )
    {
        //retrieve partyDetails
        PartyDetailResponseDTO partyDetails = nationalPartyResultService.getPartyDetails(electionId, partyId);

        // give 404 if not found
        if (partyDetails == null) {
            return ResponseEntity.notFound().build();
        }

        // return result with ok status
        return ResponseEntity.ok(partyDetails);
    }


}
