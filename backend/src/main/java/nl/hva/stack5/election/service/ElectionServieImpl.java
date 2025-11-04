package nl.hva.stack5.election.service;

import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.repository.ElectionRepository;

import java.util.Optional;

public class ElectionServieImpl implements ElectionService {

    private ElectionRepository electionRepository;

    @Override
    public Election save(Election election) {
     return electionRepository.save(election);
    }

    @Override
    public Election findById(String id) {
       return electionRepository.findById(id);
    }
}
