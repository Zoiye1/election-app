package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ReplyRepository.
 * Handles database operations for discussion replies.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@Repository
@Transactional
public class ReplyRepositoryImpl implements ReplyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ReplyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // ===== CREATE =====

    /**
     * Creates a new reply in the database with auto-generated ID.
     *
     * @param reply the reply to create
     * @return the created reply with generated ID
     */
    @Override
    public Reply Create(Reply reply) {
        entityManager.persist(reply);
        return reply;
    }

    @Override
    public Optional<Reply> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Reply> findByDiscussionId(Integer discussionId) {
        return List.of();
    }

    @Override
    public List<Reply> findByAuthorId(Long authorId) {
        return List.of();
    }

    @Override
    public Long countByDiscussionId(Integer discussionId) {
        return 0L;
    }

    @Override
    public List<Reply> findAll() {
        return List.of();
    }

    @Override
    public Reply update(Reply reply) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
