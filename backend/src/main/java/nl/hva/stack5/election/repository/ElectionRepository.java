package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Election;
import org.springframework.stereotype.Repository;

@Repository
public class ElectionRepository {

    @PersistenceContext
   private EntityManager entityManager;

    public Election findById(String id) throws Exception {
        Election election = entityManager.find(Election.class, id);
        if(election == null){
            throw new Exception("No election with id " + id);
        }
        return election;
    }
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
}
