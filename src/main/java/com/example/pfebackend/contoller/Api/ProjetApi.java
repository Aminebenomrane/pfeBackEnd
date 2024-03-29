package com.example.pfebackend.contoller.Api;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Projet;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.example.pfebackend.utils.Constants.Api_Root;

public interface ProjetApi  {


    @PostMapping(value = Api_Root + "proj/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Projet ajouterUnProjet(@RequestPart(value = "projet", required = true) Projet projet,
                           @RequestPart(value = "imageFile", required = true) MultipartFile[] file);


    @PutMapping(Api_Root + "proj/{id}")
    Projet updateUnProjet(@RequestPart("projet") Projet projet, @PathVariable int id);

    @DeleteMapping(value = Api_Root+"proj/{id}")
    void SupprimerUnProjet(@PathVariable int id);

    @GetMapping(value = Api_Root+"proj/{id}")
    Optional<Projet> getProjetById(@PathVariable  int id);
    @GetMapping(value = Api_Root+"proj/pagination/All")
    Page<Projet> tousLesProjets( @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size);
    @GetMapping(value = Api_Root+"/proj/recherche/{domaine}/{technologie}")
    List<Object[]> rechercherProjetsParDomaineEtTechnologie(@PathVariable DomaineExpertise domaine, @PathVariable Technologie technologie);
    @GetMapping(value = Api_Root+"proj/All")
    List<Projet> findAll();
    @GetMapping(value = Api_Root+"/proj/user/{userId}")
    List<Projet> getProjetByUserId(@PathVariable int userId) ;
    @GetMapping(value = Api_Root+"rechercher-projets")
    List<Projet> rechercherProjetsByCriteria(@RequestParam(required = false) Technologie technologie,
                                             @RequestParam(required = false) NatureTravail natureDuTravail,
                                             @RequestParam(required = false) Experience experience,
                                             @RequestParam(required = false) DomaineExpertise domaineExpertise);
}

