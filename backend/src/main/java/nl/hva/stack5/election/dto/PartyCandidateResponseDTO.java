package nl.hva.stack5.election.dto;

/**
 * DTO for candidate information within a party detail view.
 * Includes percentage of votes within the party.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class PartyCandidateResponseDTO {
    private long id;
    private String fullName;
    private long votes;
    private double partyPercentage;

    public PartyCandidateResponseDTO() {}

    public PartyCandidateResponseDTO(long id, String fullName, long votes, double partyPercentage) {
        this.id = id;
        this.fullName = fullName;
        this.votes = votes;
        this.partyPercentage = partyPercentage;
    }

    // Getters
    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public long getVotes() { return votes; }
    public double getPartyPercentage() { return partyPercentage; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setVotes(long votes) { this.votes = votes; }
    public void setPartyPercentage(double partyPercentage) { this.partyPercentage = partyPercentage; }
}
}
