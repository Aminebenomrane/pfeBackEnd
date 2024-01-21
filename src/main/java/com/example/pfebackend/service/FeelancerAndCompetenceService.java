package com.example.pfebackend.service;

import com.example.pfebackend.models.Competence;
import com.example.pfebackend.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface FeelancerAndCompetenceService {
    ResponseEntity<Void> addCompetenceToFreelancer(Integer id,Competence competence);
    ResponseEntity<List<Competence>> getCompetencesForFreelancer(Integer id);
    ResponseEntity<Void> removeCompetenceFromFreeLaner( Integer id,Integer competenceId);
    ResponseEntity<List<User>> getFreealncerForCompetence(Integer competenceId);

}
