package com.example.pfebackend.service;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Projet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Projet ajouterUnProjet(Projet projet);
    void supprimerUnProjet(int id);
    Projet updateUnProjet(Projet projet, int id);
    Optional<Projet> getProjetById(int id);
    Page<Projet> tousLesProjets(int page, int size);
    List<Projet> findAll();
    List<Object[]> rechercherProjetsParDomaineEtTechnologie(DomaineExpertise domaine, Technologie technologie);
    List<Projet> getProjetByUserId(int userId);
    List<Projet> rechercherProjetsByCriteria(DomaineExpertise domaineExpertise, Technologie technologie, NatureTravail natureTravail, Experience experience);
}
