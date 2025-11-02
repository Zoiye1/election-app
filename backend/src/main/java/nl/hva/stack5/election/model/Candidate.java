package nl.hva.stack5.election.model;

//TODO: make this class a Entity to be ready to migrate to database.

import jakarta.persistence.*;

/**
 * Candidate represents an individual candidate in an election,
 * storing their personal information and unique identifier.
 *
 * @author Matisse Ben Addi
 * @version 1.0
 */

@Entity
@Table(name = "candidates")
public class Candidate {

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Instance variables
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String shortCode;

    // Default constructor for JPA
    public Candidate (){
    }

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
