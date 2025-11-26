//package nl.hva.stack5.election.service;
//
//import nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO;
//import nl.hva.stack5.election.model.Election;
//import nl.hva.stack5.election.repository.MunicipalityRepository;
//import nl.hva.stack5.election.repository.ElectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//

//import java.util.List;
//
//@Service
//public class MunicipalityService {
//    @Autowired
//    private ElectionRepository electionRepository;
//    @Autowired
//    private MunicipalityRepository municipalityRepository;
//
//    /**
//     *
//     * @param electionId ho
//     * @param municipalityName
//     * @return
//     * @throws IllegalAccessException
//     */
//
//    public double CalculateVotesPercentage(String electionId, String municipalityName) throws IllegalAccessException{
//        Election election = electionRepository.findById(electionId);
//
//        if (election == null) {
//            throw new IllegalArgumentException("election not found");
//        }
//
//        long totalCounted = election.getTotalCounted();
//
//        List<MunicipalityPartyVotesDTO> results =  municipalityRepository.findByMunicipalityAndElectionId(electionId, municipalityName);
//
//        double totalMuncipalityVotes =  0;
//        for (MunicipalityPartyVotesDTO votes : results) {
//            totalMuncipalityVotes += votes.getVotes();
//        }
//
//        double MunicipalityVotesPercentage =
//                Math.round(((totalMuncipalityVotes / totalCounted) * 100) * 10) / 10.0;
//
//        return MunicipalityVotesPercentage;
//
//
//
//    }
//
//    public List<MunicipalityPartyVotesDTO> getResultsByMunicipality (String electionId, String municipalityName ) throws IllegalAccessException {
//        Election election = electionRepository.findById(electionId);
//
//        if (election == null) {
//            throw new IllegalArgumentException("election not found");
//
//        }
//        return municipalityRepository.findByMunicipalityAndElectionId(electionId, municipalityName);
//    }
//
//}
