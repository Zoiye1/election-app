package nl.hva.stack5.election.controller;

import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.service.CandidateService;
import nl.hva.stack5.election.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for national party results.
 * Provides endpoints for retrieving top parties by election.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/Parties")
@CrossOrigin(origins = "http://localhost:5173")
public class PartyResultController {

    @Autowired
    private PartyResultServiceImpl partyResultServiceImpl;

    @Autowired
    private PartyService partyService;
}
