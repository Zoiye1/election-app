package nl.hva.stack5.election.dto;


/**
 * Data Transfer Object for top candidate information.
 * Contains candidate details with their election results including votes and party affiliation.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class TopCandidateResponseDTO {

    private long id;
    private String fullName;
    private String party;
    private long votes;

    //Default constructor
    public TopCandidateResponseDTO() {}

    // Constructor with all fields
    public TopCandidateResponseDTO(long id, String fullName, String party, long votes) {
        this.id = id;
        this.fullName = fullName;
        this.party = party;
        this.votes = votes;
    }

    //GETTERS
    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getParty() {
        return party;
    }

    public long getVotes() {
        return votes;
    }

    //SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
