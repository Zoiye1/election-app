package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Election;

import java.util.Optional;

public interface ElectionService {

    Election save(Election election);
    Election findById(String id);
}
