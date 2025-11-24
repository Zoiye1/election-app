package nl.hva.stack5.election.dto;

import nl.hva.stack5.election.dto.CandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;

/**
 * Mapper for converting between Candidate entity and CandidateResponseDTO.
 * Provides static methods for entity-to-DTO conversion.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class CandidateMapper {

    /**
     * Converts a Candidate entity to a CandidateResponseDTO
     *
     * @param candidate the candidate entity to convert
     * @return CandidateResponseDTO containing data to display the candidate
     */
    public static CandidateResponseDTO toDTO(Candidate candidate) {
        if(candidate == null) {
            return null;
        }

        return new CandidateResponseDTO(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getSurname()
        );
    }
}
