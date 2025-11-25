package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.Reply;

import java.util.List;
import java.util.Optional;


/**
 * Repository interface for managing {@link Reply} entities in the database.
 * <p>
 * Provides methods for:
 * <ul>
 * <li>CRUD operations on replies</li>
 * <li>Finding replies by discussion</li>
 * <li>Finding replies by author</li>
 * <li>Counting replies for a discussion</li>
 * </ul>
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface ReplyRepository {

    // ==== CREATE ====
    /**
     * Creates and saves a reply in the database.
     * The id is automatically generated.
     *
     * @param reply The reply to Create
     * @return the created reply with auto-generated ID
     */
    Reply Create(Reply reply);


    // ==== READ ====
    /**
     * returns reply by specific ID that is called up on.
     *
     * @param id the reply ID
     * @return Optional containing the reply if found
     */
    Optional<Reply> findById (long id);


    /**
     *
     * Finds all replies for a specific discussion.
     *
     * @param discussionId The discussion identifier
     * @return List of replies for specific discussionId
     */
    List<Reply> findByDiscussionId(Integer discussionId);

    /**
     *
     * Finds all replies of a specific author.
     *
     * @param authorId The author identifier
     * @return List of replies belonging to the authorId given
     */
    List<Reply> findByAuthorId(Long authorId);


    /**
     * Counts number of replies for a specific discussion.
     *
     * @param discussionId The discussion identifier
     * @return the number of replies
     */
    Long countByDiscussionId(Integer discussionId);

    /**
     *
     * Finds all replies in database.
     *
     * @return List of all replies
     */
    List<Reply> findAll();


    // ==== UPDATE ====
    /**
     * Updates an existing reply in the database.
     *
     * @param reply the reply to update (ID must not be null)
     * @return the updated reply
     */
    Reply update(Reply reply);


    // ==== DELETE ====
    /**
     *
     * Deletes reply by its ID
     *
     * @param id The reply ID
     */
    void deleteById (long id);

}
