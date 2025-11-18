package nl.hva.stack5.election.dto;

public class ConstituencyPartyVotesDTO {

    private String registeredName;
    private String constituencyName;
    private Long votes;


    public ConstituencyPartyVotesDTO(String registeredName, String constituencyName, Long votes) {
        this.registeredName = registeredName;
        this.constituencyName = constituencyName;
        this.votes = votes;
    }

    public String getPartyName() {
        return registeredName;
    }
    public void setPartyName(String registeredName) {
        this.registeredName = registeredName;
    }
    public String getConstituencyName() {
        return constituencyName;
    }
    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }
    public Long getVotes() {
        return votes;
    }
    public void setVotes(Long votes) {
        this.votes = votes;
    }
}
