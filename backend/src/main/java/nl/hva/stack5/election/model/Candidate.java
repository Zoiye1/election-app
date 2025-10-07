package nl.hva.stack5.election.model;

public class Candidate {
    private String firstName;
    private String surname;
    private String candidateId;

    public Candidate(String firstName, String surname,  String candidateId) {
        this.firstName = firstName;
        this.surname = surname;
        this.candidateId = candidateId;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getCandidateId() {
        return candidateId;
    }
}
