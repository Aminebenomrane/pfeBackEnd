package com.example.pfebackend.repository;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProjetDAO extends JpaRepository<Projet,Integer> {
    @Query("SELECT p, p.entreprise.nomEntreprise, p.entreprise.pays, p.entreprise.numTel FROM Projet p WHERE p.domaineExpertise = :domaine AND p.technologie = :technologie")
    List<Object[]> rechercherProjetsParDomaineEtTechnologie(
            @Param("domaine") DomaineExpertise domaine,
            @Param("technologie") Technologie technologie
    );
    @Query("SELECT o FROM Projet o WHERE " +
            "(:domaineExpertise IS NULL OR o.domaineExpertise = :domaineExpertise) AND " +
            "(:technologie IS NULL OR o.technologie = :technologie) AND " +
            "(:natureTravail IS NULL OR o.natureDuTravail = :natureTravail) AND " +
            "(:experience IS NULL OR o.experience = :experience)")
    List<Projet> rechercherProjetsCriteria(
            @Param("domaineExpertise") DomaineExpertise domaineExpertise,
            @Param("technologie") Technologie technologie,
            @Param("natureTravail") NatureTravail natureTravail,
            @Param("experience") Experience experience);
    List<Projet> findByEntrepriseId(int userId);

}
