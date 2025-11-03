package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;

@Entity
@Table (name = "elections")
public class Election {
    @Id
    @Column(name = "id",  nullable = false)
    private String id;

    // mapped by election tells that election is the owner of the relationship
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<PartyConstituencyResults> partyConstituencyResults = new ArrayList<>();
    private long totalCounted;

    //Lists and maps containing info that can be retrieved.
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private Map<String, PartyResult> partyResults = new HashMap<>();

    //list of parties with Identifier and names of the parties.
    @ManyToMany(cascade = CascadeType.ALL)
    private Map<String, Party> parties = new HashMap<>();

    //List of all elected candidates
    @ManyToMany(cascade = CascadeType.ALL)
    private HashMap<String, Candidate> candidates = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL)
    private HashMap<String, CandidateResult> candidateResults = new HashMap<>();

    public Election() {}

    public Election(String id) {
        this.id = id;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public Map<String, Party> getParties() {return this.parties;}

    public Map<String, PartyResult> getPartyResults(){
        return partyResults;
    }

    public HashMap<String, Candidate> getCandidates(){
        return candidates;
    }

    public List<PartyConstituencyResults> getPartyConstituencyResults() {
     return partyConstituencyResults;
    }

    public long getTotalCounted() {
        return totalCounted;
    }

    public void setTotalCounted(long totalCounted) {
        this.totalCounted = totalCounted;
    }

    public HashMap<String, CandidateResult> getCandidateResults() {return candidateResults;}

    public void setCandidateResults(HashMap<String, CandidateResult> candidateResults) {}
}
