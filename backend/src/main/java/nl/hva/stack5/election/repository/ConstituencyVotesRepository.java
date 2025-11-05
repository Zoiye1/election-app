package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.ConstituencyVotes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConstituencyVotesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param id identifier for a constituency and the amount of votes
     * it has
     */
    public ConstituencyVotes findById(Long id)  {
        ConstituencyVotes constituencyVotes =  entityManager.find(ConstituencyVotes.class, id);
        if (constituencyVotes==null) {
            throw new EntityNotFoundException("No Constituency found with id" + id);
        }
        return constituencyVotes;
    }

    // returns a list of all constituency's and votes
    public List<ConstituencyVotes> findAllConstituencyVotes()  {
            return entityManager.createQuery("SELECT c FROM ConstituencyVotes c", ConstituencyVotes.class).getResultList();
    }

    /**
     *
     * @param constituencyName holds a name of a constituency
     */
    public ConstituencyVotes findByConstituencyName(String constituencyName) {
        try {
            return entityManager.createQuery("SELECT c FROM ConstituencyVotes c WHERE c.constituencyName = :constituencyName", ConstituencyVotes.class)
                    .getSingleResult();

        }
        catch (NoResultException e) {
            return null;
        }

    }

    @Transactional
    public ConstituencyVotes save(ConstituencyVotes constituencyVotes){
        if(constituencyVotes.getId() == null) {
            entityManager.persist(constituencyVotes);
            return constituencyVotes;
        }
        else{
           return entityManager.merge(constituencyVotes);
        }
    }
}
