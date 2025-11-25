package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.model.ConstituencyPartyVotes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConstituencyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ConstituencyPartyVotesDTO> findByConstituencyAndElectionId(
            String electionId, String constituencyName) {

        return entityManager.createQuery(
                        "SELECT new nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO(" +
                                "p.registeredName, c.name, SUM(v.votes)) " +
                                "FROM ConstituencyPartyVotes v " +
                                "JOIN v.party p " +
                                "JOIN v.constituency c " +
                                "WHERE v.election.id = :electionId " +
                                "AND c.name = :constituencyName " +
                                "GROUP BY p.registeredName, c.name " +
                                "ORDER BY SUM(v.votes) DESC",
                        ConstituencyPartyVotesDTO.class)
                .setParameter("electionId", electionId)
                .setParameter("constituencyName", constituencyName)
                .getResultList();
    }

}
