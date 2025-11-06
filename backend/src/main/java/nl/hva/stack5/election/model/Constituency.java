package nl.hva.stack5.election.model;

import jakarta.persistence.*;

@Entity
@Table(name = "constituencies")
public class Constituency {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Constituency() { }

    public Constituency(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}