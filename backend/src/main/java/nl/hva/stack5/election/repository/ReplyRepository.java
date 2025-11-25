package nl.hva.stack5.election.repository;

import nl.hva.stack5.election.model.Reply;

import java.util.Optional;

public interface ReplyRepository {


    /**
     * Saves a reply to the database.
     *
     * @param reply the reply to save
     * @return the saved reply with generated ID
     */
    Reply save(Reply reply);

    Optional<Reply> findById (long id);

}
