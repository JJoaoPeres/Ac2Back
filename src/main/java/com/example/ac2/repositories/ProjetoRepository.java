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

    // a. Método que recebe o ID do projeto e retorna o projeto com a lista de funcionários vinculados
    @Query("SELECT p FROM Projeto p JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(Integer id);

    // b. Método que recebe a data de início e fim e retorna todos os projetos com início nesse intervalo
    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}