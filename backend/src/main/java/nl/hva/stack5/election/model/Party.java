package nl.hva.stack5.election.model;

import jakarta.persistence.*;

import java.util.*;


/**
 * Party Entity holds the name of a part and the amount votes a party has
 */
@Entity
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "party_name", nullable = false)
    private String registeredName;

    @ManyToMany(mappedBy = "parties")
    private Set<Election> elections = new HashSet<>();

    public Party() {}

    /**
     *
     * @param registeredName contains the party name
     */

    public Party(String registeredName) {
        this.registeredName = registeredName;
    }

    // getters and setters
    public String getregisteredName() {
        return registeredName;
    }
    public void setregisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public Long getId() {
        return id;
    }


    public Set<Election> getElections() {return elections;}
}
