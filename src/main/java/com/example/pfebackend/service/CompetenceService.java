package com.example.pfebackend.service;


import com.example.pfebackend.models.Competence;

import java.util.List;
import java.util.Optional;

public interface CompetenceService {
    Competence AjouterUneCompetence(Competence competence);
    void SupprimerUneCompetence(int id);
    Competence UpdateUneCompetence(Competence competence,int id);

    Optional<Competence> getUneCompetenceById(int id);
    List<Competence> TousLesCompetences();
}
