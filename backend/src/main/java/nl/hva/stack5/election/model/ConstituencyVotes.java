package nl.hva.stack5.election.model;

import jakarta.persistence.*;

/**
 * This class holds the name of the constituency and the amount of votes of that constituency
 */
    @Entity
    @Table(name = "constituency_votes")
    public class ConstituencyVotes {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "constituency_name", nullable = false)
    private String constituencyName;

    @Column(name = "constituency_votes", nullable = false)
    private String votes;

    public ConstituencyVotes() {}
    /**
     *
     * @param constituencyName hols the name
     * @param votes holds the amount of votes
     */
        public ConstituencyVotes(String constituencyName, String votes) {
        this.constituencyName = constituencyName;
        this.votes = votes;
    }

    public String getConstituencyName() {
        return constituencyName;
    }
    public void setConstituencyName(String constituencyName) {
            this.constituencyName = constituencyName;
    }
    public String getVotes() {
            return votes;
    }
    public void setVotes(String Votes) {
            this.votes = votes;
    }
}
