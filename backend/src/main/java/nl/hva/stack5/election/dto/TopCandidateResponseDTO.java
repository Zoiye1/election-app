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
}
