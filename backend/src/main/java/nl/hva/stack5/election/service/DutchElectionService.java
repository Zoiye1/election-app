package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.utils.PathUtils;
import nl.hva.stack5.election.utils.xml.*;
import nl.hva.stack5.election.utils.xml.transformers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * A demo service for demonstrating how an EML-XML parser can be used inside a backend application.<br/>
 * <br/>
 * <i><b>NOTE: </b>There are some TODO's present that need fixing!</i>
 */
@Service
public class DutchElectionService {

    @Autowired
    private  ElectionRepository electionRepository;

    @Autowired
    private ConstituencyRepository constituencyRepository;

    /**
     *
     * @param electionId holds the election id which is the year
     * @return the election by the given id
     */
    public Election readResults (String electionId){
        System.out.println("fetching results for election " + electionId);
        return electionRepository.findById(electionId);
    }

    public Election importResults(String electionId, String folderName) {
        System.out.println("Processing files...");

        // Check if election already exists in database
        Election existingElection = electionRepository.findById(electionId);

        // If election exists delete from database.
        if (existingElection != null) {
            System.out.println("Election " + electionId + " already exists. Deleting old data...");
            electionRepository.delete(existingElection);
        }
        Election election = new Election(electionId);


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
            // Do what ever you like to do
            System.out.println("Dutch Election results: " + election);

            // Saves election to database
            return electionRepository.save(election);

        } catch (IOException | XMLStreamException | NullPointerException | ParserConfigurationException | SAXException e) {
            // FIXME You should do here some proper error handling! The code below is NOT how you handle errors properly!
            System.err.println("Failed to import results: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}