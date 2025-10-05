package nl.hva.stack5.election.model;

public class Candidate {
    private String firstName;
    private String surname;

    public Candidate(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
}
