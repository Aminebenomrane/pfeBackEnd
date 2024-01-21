package com.example.pfebackend.contoller.Api;


import com.example.pfebackend.models.Competence;
import com.example.pfebackend.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.pfebackend.utils.Constants.Api_Root;


public interface FreelancerAndComptenenceApi {
    @PostMapping(value=Api_Root +"/freelancers/{id}/competences")

    ResponseEntity<Void> addCompetenceToFreelancer(@PathVariable Integer id,@RequestBody Competence competence);
    @GetMapping(value=Api_Root +"/freelancers/{id}/competences")
    ResponseEntity<List<Competence>> getCompetencesForFreelancer(@PathVariable Integer id);
    @DeleteMapping(value=Api_Root +"/freelancers/{id}/competences/{competenceId}")
    ResponseEntity<Void> removeCompetenceFromFreeLaner( @PathVariable Integer id,@PathVariable  Integer competenceId);
    @GetMapping(value=Api_Root +"/freelancers/competences/{competenceId}/freelancers")
    ResponseEntity<List<User>> getFreealncerForCompetence(@PathVariable Integer competenceId);
}
