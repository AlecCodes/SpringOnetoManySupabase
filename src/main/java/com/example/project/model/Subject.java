package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjects_generator")
    private long id;


    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    //Fetchtype lazy tells Hibernate(ORM) to only fetch the related entites when needed
    //Many to one is better because we don't have to declare the connection in the parent entity first
    //It also allows us to make more changes to our repository
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "king_id", nullable = false)
    //Delete children upon deletion
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private King king;

    //Getters n setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public King getKing() {
        return king;
    }
}
