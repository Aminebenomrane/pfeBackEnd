package com.example.pfebackend.contoller.Api;


import com.example.pfebackend.models.Competence;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.pfebackend.utils.Constants.Api_Root;


public interface CompetenceApi {
    @PostMapping(value = Api_Root+"Compet/create")
    Competence AjouterUneCompetence(@RequestBody Competence competence);
    @DeleteMapping(value = Api_Root +"Compet/{id}")
    void SupprimerUneCompetence(@PathVariable int id);
    @PutMapping(value = Api_Root +"/Compet/{id}")
    Competence UpdateUneCompetence(@RequestBody Competence competence,@PathVariable int id);
    @GetMapping(value = Api_Root +"Compet/{id}")
    Optional<Competence> getUneCompetenceById(@PathVariable int id);
    @GetMapping("Compet/All")
    List<Competence> TousLesCompetences();
}

