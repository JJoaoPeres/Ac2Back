package com.example.ac2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ac2.models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    // c. Método que recebe o ID do funcionário e retorna todos os projetos aos quais ele está vinculado
    @Query("SELECT f FROM Funcionario f JOIN FETCH f.projetos WHERE f.id = :id")
    List<Funcionario> findProjetosByFuncionarioId(Integer id);
}