package nl.hva.stack5.election.dto;

/**
 * Data Transfer Object for Candidate display information.
 * Contains only user-relevant candidate data.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class CandidateResponseDTO {
    private Long candidateId;
    private String candidateName;
    private String candidateSurname;

    //constructor with fields
    public CandidateResponseDTO (Long candidateId, String candidateName, String candidateSurname) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
    }

    //Default constructor
    public CandidateResponseDTO () {}

    //GETTERS
    public Long getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCandidateSurname() {
        return candidateSurname;
    }

}
