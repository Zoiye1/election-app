package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.Candidate;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Candidate} entities in the database.
 * <p>
 * Provides methods for:
 * <ul>
 * <li>Saving candidates (single and bulk)</li>
 * <li>Finding by ID or shortCode</li>
 * <li>Retrieving all candidates</li>
 * <li>Deleting Candidates</li>
 * </ul>
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public interface CandidateRepository {

    // CREATE
    Candidate save(Candidate candidate);
    void saveAll(List<Candidate> candidates);

    // READ
    Optional<Candidate> findById(long id);
    Optional<Candidate> findByShortCode(String shortCode);
    List<Candidate> findAll();

    // DELETE
    void delete(long id);
}
