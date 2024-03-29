package com.example.pfebackend.service;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Offre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface OffreService {
    Offre ajouterUneOffre(Offre offre);

    void supprimerUneOffre(int id);

    Offre updateUneOffre(Offre offre, int id);

    Optional<Offre> getOffreById(int id);
    List<Offre> findAll();
    List<Offre> rechercherOffresByCriteria(DomaineExpertise domaineExpertise, Technologie technologie, NatureTravail natureTravail, Experience experience);
    Page<Offre> tousLesOffres(int page, int size);
    List<Object[]> rechercherOffresParDomaineEtTechnologie(DomaineExpertise domaine, Technologie technologie);
    Page<Object> getAllOffresAndProjets(int page, int size);
    List<Offre> getOffresByUserId(int userId);

}
