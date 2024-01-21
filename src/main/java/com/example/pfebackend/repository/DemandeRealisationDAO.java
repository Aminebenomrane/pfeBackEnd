package com.example.pfebackend.repository;

import com.example.pfebackend.models.DemandeRealisation;
import com.example.pfebackend.models.Enumeration.EtatDemande;
import com.example.pfebackend.models.Projet;
import com.example.pfebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface DemandeRealisationDAO extends JpaRepository<DemandeRealisation,Integer> {

    @Query("SELECT   f.titre, f.description, f.imageUrl,f.duree,d.etatD " +
            "FROM Projet f INNER JOIN f.DemandeRealisation d " +
            "WHERE d.etatD = 'Valider' AND d.freelancer.id = :idFreelancer")
    List<Object[]> findAllDemandeValidForFreealncer(@Param("idFreelancer") Integer idFreelancer);
    boolean existsByProjetAndFreelancer(Projet projet, User freelancer);
    List<DemandeRealisation> findAllByEtatD(EtatDemande etat);
    @Query("SELECT DISTINCT dr.projet FROM DemandeRealisation dr WHERE dr.freelancer.id = :idFreelancer AND dr.etatD = 'Valider'")
    List<Projet> findProjetsRealisesByFreelancerId(int idFreelancer);


}