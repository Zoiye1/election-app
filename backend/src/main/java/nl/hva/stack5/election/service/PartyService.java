package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Party;

import java.util.List;
import java.util.Optional;

public interface PartyService {
    Optional<Party> findById(String id);
    Party save(Party party);
    Optional<Party>findPartyByName(String partyName);
    List<Party> findAllParties();


}
