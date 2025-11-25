package nl.hva.stack5.election.service;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ConstituencyRepository;
import nl.hva.stack5.election.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConstituencyService {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private ConstituencyRepository constituencyRepository;

    /**
     *
     * @param electionId ho
     * @param constituencyName
     * @return
     * @throws IllegalAccessException
     */

    public double CalculateVotesPercentage(String electionId, String constituencyName) throws IllegalAccessException{
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new IllegalArgumentException("election not found");
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

    public List<ConstituencyPartyVotesDTO> getResultsByConstituency (String electionId, String constituencyName ) throws IllegalAccessException {
        Election election = electionRepository.findById(electionId);

        if (election == null) {
            throw new IllegalArgumentException("election not found");

        }
        return constituencyRepository.findByConstituencyAndElectionId(electionId, constituencyName);
    }
}
