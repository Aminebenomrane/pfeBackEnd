package com.example.pfebackend.service.Impl;

import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.example.pfebackend.models.Offre;
import com.example.pfebackend.models.Projet;
import com.example.pfebackend.repository.OffreDAO;
import com.example.pfebackend.repository.ProjetDAO;
import com.example.pfebackend.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    ProjetDAO projetDAO;
    @Autowired
    OffreDAO offreDAO;
    @Override
    public Offre ajouterUneOffre(Offre offre) {
        return offreDAO.save(offre);
    }

    @Override
    public void supprimerUneOffre(int id) {
        offreDAO.deleteById(id);
    }

    @Override
    public Offre updateUneOffre(Offre offre, int id) {
        Optional<Offre> existingOffre = offreDAO.findById(id);

        if (existingOffre.isPresent()) {
            Offre updatedOffre = existingOffre.get();
            updatedOffre.setDescription(offre.getDescription());
            updatedOffre.setPrix_heure(offre.getPrix_heure());
            updatedOffre.setDomaineExpertise(offre.getDomaineExpertise());
            updatedOffre.setTechnologie(offre.getTechnologie());
            updatedOffre.setExperience(offre.getExperience());
            return offreDAO.save(updatedOffre);
        } else {
            throw new RuntimeException("Offre non trouvée avec l'ID : " + id);
        }
    }

    @Override
    public Optional<Offre> getOffreById(int id) {
        return offreDAO.findById(id);
    }

    @Override
    public List<Offre> findAll() {
        return offreDAO.findAll();
    }

    @Override
    public List<Offre> rechercherOffresByCriteria(DomaineExpertise domaineExpertise, Technologie technologie, NatureTravail natureTravail, Experience experience) {
        return offreDAO.rechercherOffresCriteria(domaineExpertise,technologie,natureTravail,experience);
    }


    @Override
    public Page<Offre> tousLesOffres(int page, int size) {
        return offreDAO.findAll(PageRequest.of(page, size));
    }


    @Override
    public List<Object[]> rechercherOffresParDomaineEtTechnologie(DomaineExpertise domaine, Technologie technologie) {
        return offreDAO.rechercherOffresParDomaineEtTechnologie(domaine, technologie);
    }

    @Override
    public Page<Object> getAllOffresAndProjets(int page, int size) {
        Page<Offre> offres = offreDAO.findAll(PageRequest.of(page, size));
        Page<Projet> projets = projetDAO.findAll(PageRequest.of(page, size));

        List<Object> listeOffresProjets = new ArrayList<>();
        listeOffresProjets.addAll(offres.getContent());
        listeOffresProjets.addAll(projets.getContent());
        return new PageImpl<>(listeOffresProjets, PageRequest.of(page, size), offres.getTotalElements() + projets.getTotalElements());
    }

    public List<Offre> getOffresByUserId(int userId) {
        // Implement the logic to fetch offers by user ID from the repository
        return offreDAO.findByFreelancerId(userId);
    }


}
