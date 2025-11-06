package nl.hva.stack5.election.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Map;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table (name = "elections")
public class Election {
    @Id
    @Column(name = "id",  nullable = false)
    private String id;

    private long totalCounted;

    //Lists and maps containing info that can be retrieved.
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<PartyResult> partyResults = new ArrayList<>();

    //list of parties with Identifier and names of the parties.
    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey( name = "registeredName")
    private Map<String, Party> parties = new HashMap<>();

    //List of all elected candidates
    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey( name = "shortCode")
    private Map<String, Candidate> candidates = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<CandidateResult> candidateResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey( name = "name")
    private Map<String, Constituency> constituencies = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ConstituencyPartyVotes> constituencyPartyVotes = new ArrayList<>();

    public Election() {}

    public Election(String id) {
        this.id = id;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public Map<String, Party> getParties() {return this.parties;}

    public List<PartyResult> getPartyResults(){
        return partyResults;
    }

    public Map<String, Candidate> getCandidates(){
        return candidates;
    }

    public long getTotalCounted() {
        return totalCounted;
    }

    public void setTotalCounted(long totalCounted) {
        this.totalCounted = totalCounted;
    }

    public List<CandidateResult> getCandidateResults() {return candidateResults;}

    public void setCandidateResults(List<CandidateResult> candidateResults) {}

    public Map<String, Constituency> getConstituencies() {
        return constituencies;
    }

    public void setConstituencies(Map<String, Constituency> constituencies) {
        this.constituencies = constituencies;
    }

    public List<ConstituencyPartyVotes> getConstituencyPartyVotes() {
        return constituencyPartyVotes;
    }

    public void setConstituencyPartyVotes(List<ConstituencyPartyVotes> constituencyPartyVotes) {
        this.constituencyPartyVotes = constituencyPartyVotes;
    }
}