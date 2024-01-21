package com.example.pfebackend.repository;


import com.example.pfebackend.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CompetenceDAO extends JpaRepository<Competence,Integer> {
}
