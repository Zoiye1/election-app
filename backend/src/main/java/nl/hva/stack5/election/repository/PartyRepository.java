package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Party;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param registeredName holds the registered party name
     * function checks if the name exists on Party and returns it
     */

    public Party findByRegisteredName(String registeredName) {
        List<Party> results = entityManager.createQuery("SELECT p FROM Party p WHERE p.registeredName = :registeredName", Party.class)
                .setParameter("registeredName", registeredName)
                .getResultList();

        return results.isEmpty() ? null : results.get(0);
    }
    // returns all parties
    public List<Party> findAllParties() {
        try{
            return entityManager.createQuery("SELECT p FROM Party p", Party.class).getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    /**
     * @param id holds party identifier
     * searches a party based on its id
     */
    public Party findById(Long id){
        Party party = entityManager.find(Party.class, id);
        if (party == null) {
            throw new EntityNotFoundException("cannot find party with id " + id);
        }
        return party;
    }

    @Transactional
    public Party save(Party party){
        if (party.getId() == null) {
            entityManager.persist(party);
            return party;
        }
        else {
            return entityManager.merge(party);
        }
    }


}
