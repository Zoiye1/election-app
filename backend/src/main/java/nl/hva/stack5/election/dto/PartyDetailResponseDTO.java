package nl.hva.stack5.election.dto;

import java.util.List;

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

    //Default constructor
    public PartyDetailResponseDTO () {}

    // Constructor with all fields
    public PartyDetailResponseDTO(String partyName, long totalVotes, double nationalPercentage,
                                  int seats, Double previousElectionDifference,
                                  List<TopCandidateResponseDTO> candidates) {
        this.partyName = partyName;
        this.totalVotes = totalVotes;
        this.nationalPercentage = nationalPercentage;
        this.seats = seats;
        this.previousElectionDifference = previousElectionDifference;
        this.candidates = candidates;
    }

}
