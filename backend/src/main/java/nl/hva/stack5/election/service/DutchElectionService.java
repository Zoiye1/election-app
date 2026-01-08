package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.repository.PartyRepository;
import nl.hva.stack5.election.utils.PathUtils;
import nl.hva.stack5.election.utils.xml.*;
import nl.hva.stack5.election.utils.xml.transformers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DutchElectionService {
    
    @Autowired
    private ElectionRepository electionRepository;
    
    @Autowired
    private ConstituencyRepository constituencyRepository;
    
    @Autowired
    private PartyRepository partyRepository;
    
    // Cache
    private Map<String, Election> cache = new HashMap<>();
    
    public Election readResults(String electionId) {
        // Check cache
        if (cache.containsKey(electionId)) {
            System.out.println("Cache hit: " + electionId);
            return cache.get(electionId);
        }
        
        // Haal uit database
        System.out.println("Fetching from database: " + electionId);
        Election election = electionRepository.findById(electionId);
        
        // Sla op in cache
        if (election != null) {
            cache.put(electionId, election);
        }
        
        return election;
    }
    
    @Transactional
    public Election importResults(String electionId, String folderName) {
        System.out.println("Processing files...");
        
        Election existingElection = electionRepository.findById(electionId);
        if (existingElection != null) {
            System.out.println("Election " + electionId + " already exists in database. Returning existing Election");
            cache.put(electionId, existingElection); // Cache het
            return existingElection;
        }
        
        Election election = new Election(electionId);
        
        List<Party> existingParties = partyRepository.findAllParties();
        for (Party party : existingParties) {
            election.getParties().put(party.getRegisteredName(), party);
        }
        
        DutchElectionParser electionParser = new DutchElectionParser(
                new DutchDefinitionTransformer(election),
                new DutchCandidateTransformer(election),
                new DutchResultTransformer(election),
                new DutchNationalVotesTransformer(election),
                new DutchConstituencyVotesTransformer(election),
                new DutchMunicipalityVotesTransformer(election)
        );
        
        try {
            electionParser.parseResults(electionId, PathUtils.getResourcePath("/%s".formatted(folderName)));
            System.out.println("Dutch Election results: " + election);
            
            Election saved = electionRepository.save(election);
            cache.put(electionId, saved); // Cache het
            
            return saved;
        } catch (IOException | XMLStreamException | NullPointerException | ParserConfigurationException | SAXException e) {
            System.err.println("Failed to import results: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
