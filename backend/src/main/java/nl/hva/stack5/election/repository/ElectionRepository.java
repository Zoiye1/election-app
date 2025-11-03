package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Election;
import org.springframework.stereotype.Repository;

@Repository
public class ElectionRepository {

    @PersistenceContext
   private EntityManager entityManager;

    /**
     * @param id is the identifier for an election
     */
    public Election findById(String id) {
        Election election = entityManager.find(Election.class, id);
        return election;
    }

    /**
     *
     * @param election holds an instance of an election
     * function checks if there is an election and adds the election if it doesn't
     * exist
     */
    @Transactional
    public Election save(Election election){
        if (election.getId() == null) {
            entityManager.persist(election);
            return election;
        }
        else {
            return entityManager.merge(election);
        }
    }

    public void delete(Election election){
        entityManager.remove(election);
    }
}
