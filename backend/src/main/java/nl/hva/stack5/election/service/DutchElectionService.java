package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.*;
import nl.hva.stack5.election.utils.PathUtils;
import nl.hva.stack5.election.utils.xml.*;
import nl.hva.stack5.election.utils.xml.transformers.*;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * A demo service for demonstrating how an EML-XML parser can be used inside a backend application.<br/>
 * <br/>
 * <i><b>NOTE: </b>There are some TODO's present that need fixing!</i>
 */
@Service
public class DutchElectionService {

    public Election readResults(String electionId, String folderName) {
        System.out.println("Processing files...");

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
            // Assuming the election data is somewhere on the class-path it should be found.
            // Please note that you can also specify an absolute path to the folder!
            electionParser.parseResults(electionId, PathUtils.getResourcePath("/%s".formatted(folderName)));
            // Do what ever you like to do
            System.out.println("Dutch Election results: " + election);
            // Now is also the time to send the election information to a database for example.

            return election;
        } catch (IOException | XMLStreamException | NullPointerException | ParserConfigurationException | SAXException e) {
            // FIXME You should do here some proper error handling! The code below is NOT how you handle errors properly!
            System.err.println("Failed to process the election results!");
            e.printStackTrace();
            return null;
        }
    }

}
