package nl.hva.stack5.election.service;

import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ElectionRepository;
import nl.hva.stack5.election.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class MunicipalityService {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;

    public double CalculateVotesPercentage(String electionId, String municipalityName) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election not found" + electionId + "not found");
        }

        long totalCounted = election.getTotalCounted();

        List<MunicipalityPartyVotesDTO> results = municipalityRepository.findByMunicipalityAndElectionId(electionId, municipalityName);

        double totalMunicipalityVotes = 0;
        for (MunicipalityPartyVotesDTO votes : results) {
            totalMunicipalityVotes += votes.getVotes();
        }
        double municipalityVotesPercentage =
                Math.round(((totalMunicipalityVotes / totalCounted) * 100) * 10) / 10.0;
        return municipalityVotesPercentage;
    }

    public List<MunicipalityPartyVotesDTO> getResultsByMunicipality(String electionId, String municipalityName) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election not found" + electionId + "not found");
        }

        return municipalityRepository.findByMunicipalityAndElectionId(electionId, municipalityName);
    }

    public long getTotalMunicipalityVotes(String electionId, String constituencyName) throws ResponseStatusException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Election named" + electionId + "not found");
        }

        long totalCounted = election.getTotalCounted();

        List<MunicipalityPartyVotesDTO> results =  municipalityRepository.findByMunicipalityAndElectionId(electionId, constituencyName);

        long totalConstituencyVotes =  0;
        for (MunicipalityPartyVotesDTO votes : results) {
            totalConstituencyVotes += votes.getVotes();
        }
        return totalConstituencyVotes;
    }




}
