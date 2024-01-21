package com.example.pfebackend.models;


import com.example.pfebackend.models.Enumeration.NiveauCompetence;
import com.example.pfebackend.models.Enumeration.TypeCompetence;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @Enumerated(EnumType.STRING)
    private TypeCompetence typeC;

    @Enumerated(EnumType.STRING)
    private NiveauCompetence niveauC;

    @JsonIgnore
    @ManyToMany(mappedBy = "competences")
    private List<User> freelancers;

}
