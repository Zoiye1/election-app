package nl.hva.stack5.election.service;
import jakarta.persistence.EntityNotFoundException;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.dto.ConstituencyVotesDTO;
import nl.hva.stack5.election.dto.PartyVotesPerYearDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@Service
public class ConstituencyService {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private ConstituencyRepository constituencyRepository;

    /**
     *
     * @param electionId holds the identifier
     * @param constituencyName holds the name of a constituency
     * @return the votes percentage
     * @throws EntityNotFoundException
     */

    public double CalculateVotesPercentage(String electionId, String constituencyName) throws ResponseStatusException{
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

       long totalCounted = election.getTotalCounted();

        List<ConstituencyPartyVotesDTO> results =  constituencyRepository.findByConstituencyAndElectionId(electionId, constituencyName);

        double totalConstituencyVotes =  0;
        for (ConstituencyPartyVotesDTO votes : results) {
            totalConstituencyVotes += votes.getVotes();
        }

        double constituencyVotesPercentage =
                Math.round(((totalConstituencyVotes / totalCounted) * 100) * 10) / 10.0;

        return constituencyVotesPercentage;



    }

    public List<ConstituencyPartyVotesDTO> getResultsByConstituency (String electionId, String constituencyName ) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + " " + electionId + " " + "not found");
        }

        return constituencyRepository.findByConstituencyAndElectionId(electionId, constituencyName);
    }

    public long getTotalConstituencyVotes(String electionId, String constituencyName) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + " " + electionId + " " + "not found");
        }

        long totalCounted = election.getTotalCounted();

        List<ConstituencyPartyVotesDTO> results =  constituencyRepository.findByConstituencyAndElectionId(electionId, constituencyName);

        long totalConstituencyVotes =  0;
        for (ConstituencyPartyVotesDTO votes : results) {
            totalConstituencyVotes += votes.getVotes();
        }
        return totalConstituencyVotes;
    }

    public double calculateConstituencyVotesPercentage(String electionId, String constituencyName, String registeredName) throws ResponseStatusException{
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        List<ConstituencyPartyVotesDTO> results =  constituencyRepository.findByConstituencyAndElectionId(electionId, constituencyName);

        double totalConstituencyVotes =  0;
        for (ConstituencyPartyVotesDTO votes : results) {
            totalConstituencyVotes += votes.getVotes();
        }

        ConstituencyPartyVotesDTO partyResults= constituencyRepository.findPartyVotesByConstituencyAndElection(electionId, constituencyName, registeredName);



        if (partyResults == null || partyResults.getVotes() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Party " + registeredName + " not found for election " + electionId);
        }

        double partyVotes = partyResults.getVotes();


        double constituencyVotesPercentage =
                Math.round(((partyVotes / totalConstituencyVotes) * 100) * 10) / 10.0;

        return constituencyVotesPercentage;



    }

    public List<ConstituencyVotesDTO> getTop5BestPerformingConstituencyByPartyName(String electionId, String partyName) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);
        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        List<ConstituencyVotesDTO> results = constituencyRepository.findTop5PerformingConstituencyByPartyName(electionId, partyName);

        return results;
    }

    public long calculatePartyGrowth(String previousElectionId , String currentElectionId, String constituencyName, String partyName) throws ResponseStatusException {
        Election previousElection = electionRepository.findById(previousElectionId);
        if (previousElection== null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " previous Election named" + previousElectionId + "not found");
        }

        Election currentElection = electionRepository.findById(currentElectionId);

        if (currentElection== null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " current Election named" + currentElectionId + "not found");
        }

        List<String> elections = new ArrayList<>();
        elections.add(previousElectionId);
        elections.add(currentElectionId);

        List<PartyVotesPerYearDTO> results = constituencyRepository.findPartyVotesForConstituencyForYears(constituencyName, partyName, elections);

         long currentVotes = 0;
         long previousVotes = 0;


        for (PartyVotesPerYearDTO dto : results) {
            if (dto.getElectionId().equals(currentElectionId)) {
                currentVotes = dto.getVotes();
            }
            if (dto.getElectionId().equals(previousElectionId)) {
                previousVotes = dto.getVotes();
            }
        }

        if (previousVotes == 0) {
            return 0; //
        }

        long growth = currentVotes - previousVotes;


        return growth;

    }


}
