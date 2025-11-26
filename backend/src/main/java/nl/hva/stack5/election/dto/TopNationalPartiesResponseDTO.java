package nl.hva.stack5.election.dto;

public class TopNationalPartiesResponseDTO {

    private long id;
    private String partyName;
    private long votes;

    //Default constructor
    public TopNationalPartiesResponseDTO() {}

    // Constructor with all fields
    public TopNationalPartiesResponseDTO(long id, String partyName, long votes) {
        this.id = id;
        this.partyName = partyName;
        this.votes = votes;
    }

    // GETTERS
    public long getId() {
        return id;
    }

    public String getPartyName() {
        return partyName;
    }

    public long getVotes() {
        return votes;
    }

    //SETTERS

    public void setId(long id) {
        this.id = id;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }


}
