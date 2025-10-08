package nl.hva.stack5.election.model;

public class Candidate {
    private String firstName;
    private String surname;
    private String shortCode;

    public Candidate(String firstName, String surname,  String shortCode) {
        this.firstName = firstName;
        this.surname = surname;
        this.shortCode = shortCode;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getShortCode() {
        return shortCode;
    }

}
