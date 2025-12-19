package nl.hva.stack5.election.dto;

public class PartyVotesPerYearDTO {

    private String electionId;
    private Long votes;

    public PartyVotesPerYearDTO(String electionId, Long votes) {
        this.electionId = electionId;
        this.votes = votes;
    }

    public String getElectionId() {
        return electionId;
    }

    public Long getVotes() {
        return votes;
    }
}
