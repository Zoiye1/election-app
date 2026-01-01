package nl.hva.stack5.election.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nl.hva.stack5.election.model.Election;
import nl.hva.stack5.election.model.Party;
import nl.hva.stack5.election.model.PartyResult;
import nl.hva.stack5.election.repository.NationalPartyResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
}
