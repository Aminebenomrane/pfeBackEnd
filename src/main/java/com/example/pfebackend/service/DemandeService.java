package com.example.pfebackend.service;



import com.example.pfebackend.models.DemandeRealisation;
import com.example.pfebackend.models.Projet;

import java.util.List;
import java.util.Optional;

public interface DemandeService {
    DemandeRealisation createDemandeRealisation(DemandeRealisation demandeRealisation);
    DemandeRealisation validerDemandeRealisation(Integer id);
    DemandeRealisation annulerDemandeRealisation(Integer id);
    Optional<DemandeRealisation> findById(Integer id);
    List<DemandeRealisation> findAll();
    List<Object[]> findAllValidDemande( Integer idfreelancer);
    List<DemandeRealisation> findAllEnCours();
    List<DemandeRealisation> findAllAnnuler();
    List<DemandeRealisation> findAllValider();

    void delete(int id);
    List<Projet> findProjetsRealisesByFreelancerId(int idFreelancer);

}
