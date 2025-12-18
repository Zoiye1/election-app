package nl.hva.stack5.election.dto;

public class ConstituencyVotesDTO {

    private String constituencyName;
    private Long votes;

    public ConstituencyVotesDTO(String constituencyName, Long votes) {
        this.constituencyName = constituencyName;
        this.votes = votes;
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
