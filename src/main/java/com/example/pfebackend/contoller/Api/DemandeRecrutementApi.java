package com.example.pfebackend.contoller.Api;


import com.example.pfebackend.models.DemandeRecrutement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.pfebackend.utils.Constants.Api_Root;

public interface DemandeRecrutementApi {
    @PostMapping(value = Api_Root+"demandeRec/create")
    DemandeRecrutement createDemandeRecrutement(@RequestBody DemandeRecrutement demandeRecrutement);
    @PutMapping(value = Api_Root +"/validerRec/{id}")
    DemandeRecrutement validerDemandeRecrutement(@PathVariable Integer id);
    @PutMapping(value = Api_Root +"/annulerRec/{id}")
    DemandeRecrutement  annulerDemandeRecrutement(@PathVariable Integer id);
    @GetMapping(value = Api_Root +"deleteRec/{id}")
    Optional< DemandeRecrutement> findById(@PathVariable Integer id);
    @GetMapping(value = Api_Root +"demandeRec/all")
    List< DemandeRecrutement> findAll();
    @GetMapping(value = Api_Root+"/demandeRec/valid/{iden}")
    List<Object[]> findAllValidDemande( @PathVariable Integer iden);
    @GetMapping(value = Api_Root+"/demandeRec/encours/All")
    List< DemandeRecrutement> findAllEnCours();
    @GetMapping(value = Api_Root+"/demandeRec/annuler/All")
    List< DemandeRecrutement> findAllAnnuler();
    @GetMapping(value = Api_Root+"/demandeRec/valider/All")
    List< DemandeRecrutement> findAllValider();
    @DeleteMapping(value = Api_Root +"deleteRec/{id}")
    void delete(int id);
}
