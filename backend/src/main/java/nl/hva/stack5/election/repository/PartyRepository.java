package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Party;
import org.springframework.stereotype.Repository;

@Repository
public class PartyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param registeredName holds the registered party name
     * function checks if the name exists on Party and returns it
     */

    public Party findByRegisteredName(String registeredName) {
        try {
            return entityManager.createQuery("SELECT p FROM Party p WHERE p.registeredName LIKE:registeredName ", Party.class).getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }



    }

    /**
     *
     * @param id holds party identifier
     * searches a party based on its id
     */
    public Party findById(Long id) throws Exception{
        Party party = entityManager.find(Party.class, id);
        if (party == null) {
            throw new Exception("cannot find party with id " + id);
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
