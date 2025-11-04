package nl.hva.stack5.election.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Instance variables
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String shortCode;

    @ManyToMany(mappedBy = "candidates")
    private Set<Election> elections = new HashSet<>();

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

    public Set<Election> getElections() {
        return elections;
    }
}
