package nl.hva.stack5.election.dto;

public class MunicipalityPartyVotesDTO {

        private String municipalityName;
        private String partyName;
        private int votes;

        // Constructor
        public MunicipalityPartyVotesDTO(String municipalityName, String partyName, int votes) {
            this.municipalityName = municipalityName;
            this.partyName = partyName;
            this.votes = votes;
        }

        // Getters
        public String getMunicipalityName() {
            return municipalityName;
        }

        public String getPartyName() {
            return partyName;
        }

        public int getVotes() {
            return votes;
        }

        // Setters (optioneel, meestal niet nodig voor response DTOs)
        public void setMunicipalityName(String municipalityName) {
            this.municipalityName = municipalityName;
        }

        public void setPartyName(String partyName) {
            this.partyName = partyName;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }
    }

