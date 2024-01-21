package com.example.pfebackend.service;


import com.example.pfebackend.models.DemandeRecrutement;

import java.util.List;
import java.util.Optional;

public interface DemandeRecrutementService {
    DemandeRecrutement createDemandeRecrutement(DemandeRecrutement demandeRecrutement);
    DemandeRecrutement validerDemandeRecrutement(Integer id);
    DemandeRecrutement  annulerDemandeRecrutement(Integer id);
    Optional< DemandeRecrutement> findById(Integer id);
    List< DemandeRecrutement> findAll();
    List<Object[]> findAllValidDemande( Integer iden);
    List< DemandeRecrutement> findAllEnCours();
    List< DemandeRecrutement> findAllAnnuler();
    List< DemandeRecrutement> findAllValider();

    void delete(int id);
}
