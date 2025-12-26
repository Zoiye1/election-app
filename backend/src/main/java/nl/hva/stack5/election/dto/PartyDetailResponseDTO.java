package nl.hva.stack5.election.dto;

/**
 * DTO for detailed party information including elected candidates
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class PartyDetailResponseDTO {

    private String partyName;
    private long totalVotes;
    private double nationalPercentage;
    private int seats;
    private Double previousElectionDifference;
    private List<TopCandidateResponseDTO> candidates;

}
