package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import nl.hva.stack5.election.model.Candidate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CandidateRepositoryImpl implements CandidateRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Candidate save(Candidate candidate) {
        if (candidate == null) {
            entityManager.persist(candidate);
            return candidate;
        }
        return entityManager.merge(candidate);
    }

    @Override
    public void saveAll(List<Candidate> candidates) {
        entityManager.persist(candidates);
    }

    @Override
    public Optional<Candidate> findById(long id) {
        Candidate candidate = entityManager.find(Candidate.class, id);
        return Optional.ofNullable(candidate);
    }

    @Override
    public Optional<Candidate> findByShortCode(String shortCode) {
        try {
            TypedQuery<Candidate> query = entityManager.createQuery(
                    "SELECT c FROM Candidate c WHERE c.shortCode = :short_code", Candidate.class);
            query.setParameter("short_code", shortCode);
            Candidate result = query.getSingleResult();
            return Optional.ofNullable(result);  // ← Handelt null automatisch af
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Candidate> findAll() {
        TypedQuery<Candidate> query = entityManager.createQuery(
                "SELECT c FROM Candidate c",
                Candidate.class
        );
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        Candidate candidate = entityManager.find(Candidate.class, id);
        if (candidate != null) {
            entityManager.remove(candidate);
        }
    }
}
