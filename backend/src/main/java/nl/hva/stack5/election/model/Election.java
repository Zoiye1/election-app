package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table (name = "elections")
public class Election {
    @Id
    @Column(name = "id",  nullable = false)
    private String id;

    // mapped by election tells that election is the owner of the relationship
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<PartyConstituencyResults> partyConstituencyResults = new ArrayList<>();

    public Election() {}

    public Election(String id) {
        this.id = id;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public List<PartyConstituencyResults> getPartyConstituencyResults() {
     return partyConstituencyResults;

    }

    public void setPartyConstituencyResults(List<PartyConstituencyResults> results) {
        this.partyConstituencyResults = results;
    }
}
