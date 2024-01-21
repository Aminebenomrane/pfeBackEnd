package com.example.pfebackend.contoller;


import com.example.pfebackend.contoller.Api.DemandeApi;
import com.example.pfebackend.models.DemandeRealisation;
import com.example.pfebackend.models.Projet;
import com.example.pfebackend.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class DemandeController implements DemandeApi {
    @Autowired
    DemandeService demandeService;
    @Override
    public DemandeRealisation createDemandeRealisation(DemandeRealisation demandeRealisation) {
        return demandeService.createDemandeRealisation(demandeRealisation);
    }

    @Override
    public DemandeRealisation validerDemandeRealisation(Integer id) {
        return demandeService.validerDemandeRealisation(id);
    }

    @Override
    public DemandeRealisation annulerDemandeRealisation(Integer id) {
        return demandeService.annulerDemandeRealisation(id);
    }

    @Override
    public Optional<DemandeRealisation> findById(Integer id) {
        return demandeService.findById(id);
    }

    @Override
    public List<DemandeRealisation> findAll() {
        return demandeService.findAll();
    }

    @Override
    public List<Object[]> findAllValidDemande(Integer idfreelancer) {
        return demandeService.findAllValidDemande(idfreelancer);
    }

    @Override
    public List<DemandeRealisation> findAllEnCours() {
        return demandeService.findAllEnCours();
    }

    @Override
    public List<DemandeRealisation> findAllAnnuler() {
        return demandeService.findAllAnnuler();
    }

    @Override
    public List<DemandeRealisation> findAllValider() {
        return demandeService.findAllValider();
    }

    @Override
    public void delete(int id) {
        demandeService.delete(id);
    }

    @Override
    public List<Projet> findProjetsRealisesByFreelancerId(int idFreelancer) {
        return demandeService.findProjetsRealisesByFreelancerId(idFreelancer);
    }
}
