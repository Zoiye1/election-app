package nl.hva.stack5.election.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for NationalPartyResultRepository.
 * Tests actual database queries using H2 in-memory database.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class NationalPartyResultRepositoryIntegrationTest {

    @Autowired
    private NationalPartyResultRepository nationalPartyResultRepository;

    @Autowired
    private EntityManager entityManager;

    private Election testElection;
    private Party vvdParty;
    private Party pvvParty;

    @BeforeEach
    void setUp() {
        //create an election
        testElection = new Election("TK2023");
        entityManager.persist(testElection);
        // create 2 parties
        vvdParty = new Party("VVD");
        pvvParty = new Party("PVV");
        entityManager.persist(vvdParty);
        entityManager.persist(pvvParty);
        // create partyResults for parties
        PartyResult vvdResult = new PartyResult(testElection, vvdParty, 2000000L);
        PartyResult pvvResult = new PartyResult(testElection, pvvParty, 2500000L);
        entityManager.persist(vvdResult);
        entityManager.persist(pvvResult);

        // send all above to DB
        entityManager.flush();
    }

    @Test
    void findTopByElectionYear_ShouldReturnPartiesOrderedByVotes() {
        //act
        List<PartyResult> results = nationalPartyResultRepository.findTopByElectionYear("TK2023", 10);
        //assert
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("PVV", results.get(0).getParty().getRegisteredName());
        assertEquals("VVD", results.get(1).getParty().getRegisteredName());
    }
}
