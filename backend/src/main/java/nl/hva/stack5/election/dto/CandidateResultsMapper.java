package nl.hva.stack5.election.dto;

import nl.hva.stack5.election.dto.TopCandidateResponseDTO;
import nl.hva.stack5.election.model.Candidate;
import nl.hva.stack5.election.model.CandidateResult;


/**
 * Mapper for converting between CandidateResult entity and TopCandidateResponseDTO.
 * Provides static methods for entity-to-DTO conversion.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class CandidateResultsMapper {

    /**
     * Converts a CandidateResult entity to a TopCandidateResponseDTO.
     * Combines candidate's first name and surname into full name.
     *
     * @param result the candidate result entity to convert
     * @return TopCandidateResponseDTO containing candidate ranking data
     */
    public static TopCandidateResponseDTO toDTO(CandidateResult result) {

    }
}
