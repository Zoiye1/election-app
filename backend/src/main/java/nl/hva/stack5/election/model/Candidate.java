package nl.hva.stack5.election.model;

//TODO: make this class a Entity to be ready to migrate to database.

/**
 * Candidate represents an individual candidate in an election,
 * storing their personal information and unique identifier.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */
public class Candidate {
    // Instance variables
    private String firstName;
    private String surname;
    private String shortCode;



    /**
     * Constructs a Candidate with the specified personal information and identifier.
     *
     * @param firstName the first name of the candidate
     * @param surname the surname of the candidate
     * @param shortCode the unique identifier for the candidate
     */
    public Candidate(String firstName, String surname,  String shortCode) {
        this.firstName = firstName;
        this.surname = surname;
        this.shortCode = shortCode;
    }

    // Getters
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
