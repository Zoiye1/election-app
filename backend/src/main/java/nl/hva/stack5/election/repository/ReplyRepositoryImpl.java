package nl.hva.stack5.election.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Reply;
import nl.hva.stack5.election.model.User;
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

    // ===== READ =====

    /**
     *
     * Finds the reply by ID
     *
     * @param id the reply ID
     * @return Optional containing the reply if found
     */
    @Override
    public Optional<Reply> findById(long id) {
        Reply reply = entityManager.find(Reply.class, id);
        return Optional.ofNullable(reply);
    }


    /**
     *Finds all replies for a specific discussion.
     * Ordered from oldest to newest.
     *
     * @param discussionId The discussion identifier
     * @return List of replies for the discussion
     */
    @Override
    public List<Reply> findByDiscussionId(Integer discussionId) {
        TypedQuery<Reply> query = entityManager.createQuery(
                "SELECT r FROM Reply r " + // Select all replies
                        "WHERE r.discussion.id = :discussionId " + // Filter by specific discussion
                        "ORDER BY r.createdAt ASC", // Order oldest first
                Reply.class
        );
        // Bind the discussionId parameter to the :discussionId placeholder in the query
        query.setParameter("discussionId", discussionId);
        return query.getResultList();

    }


    /**
     * Finds all replies by a specific author.
     * ordered from oldest to newest.
     *
     * @param authorId the user/author identifier
     * @return List of replies by the author
     */
    @Override
    public List<Reply> findByAuthorId(Long authorId) {
        TypedQuery<Reply> query = entityManager.createQuery(
                "SELECT r FROM Reply r" + // Select all replies
                    " WHERE r.author.id = :authorId " + // filter by authorId
                "ORDER BY r.createdAt ASC", // Order oldest first
                Reply.class
        );
        query.setParameter("authorId", authorId);
        // Bind the discussionId parameter to the :discussionId placeholder in the query
        return query.getResultList();
    }


    /**
     * Counts the number of replies for a specific discussion.
     *
     * @param discussionId the discussion identifier
     * @return the number of replies
     */
    @Override
    public Long countByDiscussionId(Integer discussionId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(r) FROM Reply r" + // Counts all replies found
                        " WHERE r.discussion.id = :discussionId", // for the specific discussionId
                Long.class
        );
        // Bind the discussionId parameter to the :discussionId placeholder in the query
        query.setParameter("discussionId", discussionId);
        return query.getSingleResult();
    }

    /**
     * Finds all replies in the database.
     * Ordered from oldest to newest.
     *
     * @return List of all replies
     */
    @Override
    public List<Reply> findAll() {
        TypedQuery<Reply> query = entityManager.createQuery(
                "SELECT r FROM Reply r ORDER BY r.createdAt ASC",
                Reply.class
        );
        return query.getResultList();
    }

    // ==== UPDATE ====

    @Override
    public Reply update(Reply reply) {
        return null;
    }

    // ==== DELETE ====

    @Override
    public void deleteById(long id) {

    }
}
