package nl.hva.stack5.election.dto;
/**
 * Data Transfer Object for top PartyResult information.
 * Contains partyResult details with their election results including votes and party affiliation.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class TopNationalPartiesResponseDTO {

    private long partyId;
    private String partyName;
    private long votes;

    //Default constructor
    public TopNationalPartiesResponseDTO() {}

    // Constructor with all fields
    public TopNationalPartiesResponseDTO(String partyName, long votes) {
        this.partyName = partyName;
        this.votes = votes;
        this.partyId = partyId;
    }

    // GETTERS
    public String getPartyName() {
        return partyName;
    }

    public long getVotes() {
        return votes;
    }

    public long getPartyId() { return partyId; }

    //SETTERS
    public void setVotes(long votes) {
        this.votes = votes;
    }


}
