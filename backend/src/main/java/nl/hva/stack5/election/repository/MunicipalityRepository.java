package nl.hva.stack5.election.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MunicipalityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<MunicipalityPartyVotesDTO> findByMunicipalityAndElectionId(
            String electionId, String municipalityName) {

        return entityManager.createQuery(
                        "SELECT new nl.hva.stack5.election.dto.MunicipalityPartyVotesDTO(" +
                                "p.registeredName, m.name, SUM(v.votes)) " +
                                "FROM MunicipalityPartyVotes v " +
                                "JOIN v.party p " +
                                "JOIN v.municipality m " +
                                "WHERE v.election.id = :electionId " +
                                "AND m.name = :municipalityName " +
                                "GROUP BY p.registeredName, m.name " +
                                "ORDER BY SUM(v.votes) DESC",
                        MunicipalityPartyVotesDTO.class)
                .setParameter("electionId", electionId)
                .setParameter("municipalityName", municipalityName)
                .getResultList();
    }

}
