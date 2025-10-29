package nl.hva.stack5.election.model;

public class Candidate {
    private String firstName;
    private String surname;
    private String shortCode;



    /**
     *
     * @param firstName the first name of a candidate
     * @param surname the surname of a candidate
     * @param shortCode the unique identifier of a elected candidate
     */

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
