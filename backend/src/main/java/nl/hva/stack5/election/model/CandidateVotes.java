package nl.hva.stack5.election.model;

public class CandidateVotes {

  private String shortCode;
  private String nationalVotes;

  CandidateVotes(String shortCode, String nationalVotes) {
      this.shortCode = shortCode;
      this.nationalVotes = nationalVotes;
  }

  public String getNationalVotes() {
    return nationalVotes;
  }

}
