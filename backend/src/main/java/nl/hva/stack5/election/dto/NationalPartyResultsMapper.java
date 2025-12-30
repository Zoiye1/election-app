package nl.hva.stack5.election.dto;

import nl.hva.stack5.election.model.PartyResult;

/**
 * Mapper for converting between PartyResult entity and TopNationalPartiesResponseDTO.
 * Provides static methods for entity-to-DTO conversion.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class NationalPartyResultsMapper {

    /**
     * Converts a PartyResult entity to a TopNationalPartiesResponseDTO.
     *
     * @param result the party result entity to convert
     * @return TopNationalPartiesResponseDTO containing party ranking data
     */
    public static TopNationalPartiesResponseDTO toDTO(PartyResult result) {
        if (result == null) {
            return null;
        }

        return new TopNationalPartiesResponseDTO(
                result.getParty().getRegisteredName(),
                result.getVotes()
        );
    }
}
