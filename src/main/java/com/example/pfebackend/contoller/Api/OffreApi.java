package com.example.pfebackend.contoller.Api;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Offre;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.example.pfebackend.utils.Constants.Api_Root;

public interface OffreApi {
    @PostMapping(value = Api_Root+"offer/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Offre ajouterUneOffre(@RequestPart(value = "offre")  Offre offre , @RequestPart(value = "imageFile") MultipartFile[] file);
    @DeleteMapping(value = Api_Root+"offer/{id}")
    void supprimerUneOffre(@PathVariable int id);
    @PutMapping(value = Api_Root+"offer/{id}")
    Offre updateUneOffre(@RequestBody Offre offre,@PathVariable int id);
    @GetMapping(value = Api_Root+"offer/{id}")
    Optional<Offre> getOffreById(@PathVariable int id);
    @GetMapping(value = Api_Root+"offer/pagination/All")
    Page<Offre> tousLesOffres(  @RequestParam(defaultValue = "0")int page,   @RequestParam(defaultValue = "7") int size);
    @GetMapping(value = Api_Root+"offer/recherche/{domaine}/{technologie}")
    List<Object[]> rechercherOffresParDomaineEtTechnologie(
            @PathVariable("domaine") DomaineExpertise domaine,
            @PathVariable("technologie") Technologie technologie
    );
    @GetMapping(value = Api_Root+"offer/All")
    List<Offre> findAll();
    @GetMapping(value = Api_Root+"/offres-et-projets")
    Page<Object> getAllOffresAndProjets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = Api_Root+"/offer/user/{userId}")
    List<Offre> getOffresByUserId(@PathVariable int userId) ;
    @GetMapping(value = Api_Root+"rechercher-offres")
    List<Offre> rechercherOffresByCriteria(@RequestParam(required = false) Technologie technologie,
                                           @RequestParam(required = false) NatureTravail natureDuTravail,
                                           @RequestParam(required = false) Experience experience,
                                           @RequestParam(required = false) DomaineExpertise domaineExpertise);
}
