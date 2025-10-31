package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.PartyConstituencyResults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartyConstituencyResultsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param election holds a certain election
     * @return election with a list of party constituency results
     */
    public List<PartyConstituencyResults> findByElection(Election election){
        return entityManager.createQuery("SELECT pcr FROM PartyConstituencyResults pcr WHERE pcr.election = :election",PartyConstituencyResults.class)
                .setParameter("election",election)
                .getResultList();
    }

    /**
     *
     * @param id identifier for a party constituency results
     */
    public PartyConstituencyResults findById(Long id){
        PartyConstituencyResults partyConstituencyResults = entityManager.find(PartyConstituencyResults.class, id);
        if(partyConstituencyResults == null){
            throw new EntityNotFoundException("PartyConstituencyResults with id " + id + " not found");
        }
        return partyConstituencyResults;
    }
    // returns all constituency results
    public List<PartyConstituencyResults> findAllConstituencyResults() {
        return entityManager.createQuery("select p from PartyConstituencyResults p", PartyConstituencyResults.class).getResultList();
    }

    @Transactional
    // saves the party constituency results if it doesn't already exist
    public PartyConstituencyResults save(PartyConstituencyResults partyConstituencyResults) {
        if (partyConstituencyResults.getId() == null) {
            entityManager.persist(partyConstituencyResults);
            return partyConstituencyResults;
        }
        else {
            return entityManager.merge(partyConstituencyResults);
        }
    }
}
