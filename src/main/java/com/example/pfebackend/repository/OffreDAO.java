package com.example.pfebackend.repository;

import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OffreDAO extends JpaRepository<Offre,Integer> {
    @Query("SELECT o, o.freelancer.firstname,o.freelancer.lastname,o.freelancer.numTel,o.freelancer.pays FROM Offre o WHERE o.domaineExpertise = :domaine AND o.technologie = :technologie")
    List<Object[]> rechercherOffresParDomaineEtTechnologie(
            @Param("domaine") DomaineExpertise domaine,
            @Param("technologie") Technologie technologie
    );
    @Query("SELECT o FROM Offre o " +
            "WHERE (:technologie IS NULL OR o.technologie = :technologie) " +
            "AND (:natureTravail IS NULL OR o.natureDuTravail = :natureTravail) " +
            "AND (:experience IS NULL OR o.experience = :experience) " +
            "AND (:domaineExpertise IS NULL OR o.domaineExpertise = :domaineExpertise)")
    List<Offre> rechercherOffresCriteria(
            @Param("domaineExpertise") DomaineExpertise domaineExpertise,
            @Param("technologie") Technologie technologie,
            @Param("natureTravail") NatureTravail natureTravail,
            @Param("experience") Experience experience);


    List<Offre> findByFreelancerId(int userId);
}
