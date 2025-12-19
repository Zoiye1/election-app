package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO;
import nl.hva.stack5.election.dto.ConstituencyVotesDTO;
import org.springframework.stereotype.Repository;
import nl.hva.stack5.election.dto.PartyVotesPerYearDTO;
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

    public ConstituencyPartyVotesDTO findPartyVotesByConstituencyAndElection(
            String electionId,
            String constituencyName,
            String partyName
    ) {

        List<ConstituencyPartyVotesDTO> results =
                entityManager.createQuery(
                                "SELECT new nl.hva.stack5.election.dto.ConstituencyPartyVotesDTO(" +
                                        "p.registeredName, c.name, SUM(v.votes)) " +
                                        "FROM ConstituencyPartyVotes v " +
                                        "JOIN v.party p " +
                                        "JOIN v.constituency c " +
                                        "WHERE v.election.id = :electionId " +
                                        "AND c.name = :constituencyName " +
                                        "AND p.registeredName = :partyName " +
                                        "GROUP BY p.registeredName, c.name",
                                ConstituencyPartyVotesDTO.class)
                        .setParameter("electionId", electionId)
                        .setParameter("constituencyName", constituencyName)
                        .setParameter("partyName", partyName)
                        .getResultList();

        return results.isEmpty() ? null : results.get(0);
    }


    public List<ConstituencyVotesDTO> findTop5PerformingConstituencyByPartyName(String electionId, String partyName) {
        return entityManager.createQuery(
                        "SELECT new nl.hva.stack5.election.dto.ConstituencyVotesDTO(" +
                                "c.name, SUM(v.votes)) " +
                                "FROM ConstituencyPartyVotes v " +
                                "JOIN v.party p " +
                                "JOIN v.constituency c " +
                                "WHERE v.election.id = :electionId " +
                                "AND p.registeredName = :partyName " +
                                "GROUP BY c.name " +
                                "ORDER BY SUM(v.votes) DESC",
                        ConstituencyVotesDTO.class
                )
                .setParameter("electionId", electionId)
                .setParameter("partyName", partyName)
                .setMaxResults(5)
                .getResultList();
    }
    public List<PartyVotesPerYearDTO> findPartyVotesForConstituencyForYears(
            String constituencyName,
            String partyName,
            List<String> electionIds
    ) {
        return entityManager.createQuery(
                        "SELECT new nl.hva.stack5.election.dto.PartyVotesPerYearDTO(" +
                                "e.id, SUM(v.votes)) " +
                                "FROM ConstituencyPartyVotes v " +
                                "JOIN v.election e " +
                                "JOIN v.party p " +
                                "JOIN v.constituency c " +
                                "WHERE c.name = :constituencyName " +
                                "AND p.registeredName = :partyName " +
                                "AND e.id IN :electionIds " +
                                "GROUP BY e.id",
                        PartyVotesPerYearDTO.class
                )
                .setParameter("constituencyName", constituencyName)
                .setParameter("partyName", partyName)
                .setParameter("electionIds", electionIds)
                .getResultList();
    }




}
