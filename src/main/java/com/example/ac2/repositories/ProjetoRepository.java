package com.example.ac2.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ac2.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    
    @Query("SELECT p FROM Projeto p JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(Integer id);

    
    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}