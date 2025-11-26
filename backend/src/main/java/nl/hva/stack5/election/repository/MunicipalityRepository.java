//package nl.hva.stack5.election.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO;
//
//import java.util.List;
//
//public class MunicipalityRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<MunicipalityPartyVotesDTO> findByMunicipalityAndElectionId(
//            String electionId, String municipalityName) {
//
//        return entityManager.createQuery(
//                        "SELECT new nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO(" +
//                                "p.registeredName, c.name, SUM(v.votes)) " +
//                                "FROM MunicipalityPartyVotes v " +
//                                "JOIN v.party p " +
//                                "JOIN v.municipality c " +
//                                "WHERE v.election.id = :electionId " +
//                                "AND c.name = :municipalityName " +
//                                "GROUP BY p.registeredName, c.name " +
//                                "ORDER BY SUM(v.votes) DESC",
//                        MunicipalityPartyVotesDTO.class)
//                .setParameter("electionId", electionId)
//                .setParameter("municipalityName", municipalityName)
//                .getResultList();
//    }
//}
