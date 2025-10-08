package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    List<Discussion> findAllByOrderByCreatedAtDesc();
}