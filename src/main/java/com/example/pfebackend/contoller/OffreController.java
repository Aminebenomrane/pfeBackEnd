package com.example.pfebackend.contoller;


import com.example.pfebackend.contoller.Api.OffreApi;
import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.ImageModel;
import com.example.pfebackend.models.Offre;
import com.example.pfebackend.service.OffreService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OffreController implements OffreApi {
    @Autowired
    OffreService offreService;

    public Offre ajouterUneOffre(@RequestPart(value = "offre")  Offre offre , @RequestPart(value = "imageFile") MultipartFile[] file) {



        try {
            Set<ImageModel> images = uploadImage(file);
            offre.setProductImagess(images);
            return offreService.ajouterUneOffre(offre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<ImageModel>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @Override
    public void supprimerUneOffre(int id) {
        offreService.supprimerUneOffre(id);
    }

    @Override
    public Offre updateUneOffre(Offre offre, int id) {
        return offreService.updateUneOffre(offre,id);
    }

    @Override
    public Optional<Offre> getOffreById(int id) {
        return offreService.getOffreById(id);
    }

    @Override
    public Page<Offre> tousLesOffres(int page, int size) {
        return offreService.tousLesOffres(page,size);
    }

    @Override
    public List<Object[]> rechercherOffresParDomaineEtTechnologie(@PathVariable DomaineExpertise domaine, @PathVariable Technologie technologie) {
        return offreService.rechercherOffresParDomaineEtTechnologie(domaine,technologie);
    }

    @Override
    public List<Offre> findAll() {
        return offreService.findAll();
    }

    @Override
    public Page<Object> getAllOffresAndProjets(int page, int size) {
        return offreService.getAllOffresAndProjets(page,size);
    }

    @Override
    public List<Offre> getOffresByUserId(int userId) {
        return offreService.getOffresByUserId(userId);
    }

    @Override
    public List<Offre> rechercherOffresByCriteria(Technologie technologie, NatureTravail natureDuTravail, Experience experience, DomaineExpertise domaineExpertise) {
        return offreService.rechercherOffresByCriteria(domaineExpertise, technologie, natureDuTravail, experience);
    }


}
