package com.example.pfebackend.contoller;

import com.example.pfebackend.contoller.Api.CompetenceApi;
import com.example.pfebackend.models.Competence;
import com.example.pfebackend.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CompetenceController implements CompetenceApi {
    @Autowired
    CompetenceService competenceService;
    @Override
    public Competence AjouterUneCompetence(Competence competence) {
        return competenceService.AjouterUneCompetence(competence);
    }

    @Override
    public void SupprimerUneCompetence(int id) {
        competenceService.SupprimerUneCompetence(id);
    }

    @Override
    public Competence UpdateUneCompetence(Competence competence, int id) {
        return competenceService.UpdateUneCompetence(competence,id);
    }

    @Override
    public Optional<Competence> getUneCompetenceById(int id) {
        return competenceService.getUneCompetenceById(id);
    }

    @Override
    public List<Competence> TousLesCompetences() {
        return competenceService.TousLesCompetences();
    }
}
