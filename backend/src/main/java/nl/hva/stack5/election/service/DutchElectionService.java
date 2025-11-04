package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.utils.PathUtils;
import nl.hva.stack5.election.utils.xml.*;
import nl.hva.stack5.election.utils.xml.transformers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Optional;

/**
 * A demo service for demonstrating how an EML-XML parser can be used inside a backend application.<br/>
 * <br/>
 * <i><b>NOTE: </b>There are some TODO's present that need fixing!</i>
 */
@Service
public class DutchElectionService {

    @Autowired
    private DutchConstituencyVotesTransformer dutchConstituencyVotesTransformer;

    @Autowired
    private  ElectionRepository electionRepository;

    public Election readResults(String electionId, String folderName) {
        System.out.println("Processing files...");

        // Check if election already exists in database
        Election existingElection = electionRepository.findById(electionId);

        // If election exists delete from database.
        if (existingElection != null) {
            System.out.println("Election " + electionId + " already exists. Deleting old data...");
            electionRepository.delete(existingElection);
        }

        // sets the given election in the transformer
        dutchConstituencyVotesTransformer.setElection(election);

        Election election = new Election(electionId);
        DutchElectionParser electionParser = new DutchElectionParser(
                new DutchDefinitionTransformer(election),
                new DutchCandidateTransformer(election),
                new DutchResultTransformer(election),
                new DutchNationalVotesTransformer(election),
                dutchConstituencyVotesTransformer,
                new DutchMunicipalityVotesTransformer(election)
        );

        try {
            // Assuming the election data is somewhere on the class-path it should be found.
            // Please note that you can also specify an absolute path to the folder!
            electionParser.parseResults(electionId, PathUtils.getResourcePath("/%s".formatted(folderName)));
            // Do what ever you like to do
            System.out.println("Dutch Election results: " + election);

            // Saves election to database
            return electionRepository.save(election);

        } catch (IOException | XMLStreamException | NullPointerException | ParserConfigurationException | SAXException e) {
            // FIXME You should do here some proper error handling! The code below is NOT how you handle errors properly!
            System.err.println("Failed to process the election results!");
            e.printStackTrace();
            return null;
        }
    }

}
