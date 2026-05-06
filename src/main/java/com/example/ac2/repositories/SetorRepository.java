package com.example.ac2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ac2.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

    // d. Método que lista todos os setores com seus respectivos funcionários vinculados
    @Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
    List<Setor> findAllWithFuncionarios();
}