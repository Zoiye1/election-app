package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Election;

import java.util.Optional;

public interface PartyConstituencyResults {
    Optional<PartyConstituencyResults> findById(long id);
    PartyConstituencyResults save(PartyConstituencyResults partyConstituencyResults);
    PartyConstituencyResults findAllConstituencyResults();
    PartyConstituencyResults findByElectionId(Election election);
}
