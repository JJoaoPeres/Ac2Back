package com.example.ac2;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;
import com.example.ac2.repositories.SetorRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class Ac2Application {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Bean
    @Transactional
    public CommandLineRunner init() {
        return args -> {
            
            Setor setor1 = new Setor(null, "TI", null);
            Setor setor2 = new Setor(null, "Marketing", null);
            setor1 = setorRepository.save(setor1);
            setor2 = setorRepository.save(setor2);

           
            Projeto projeto1 = new Projeto(null, "Desenvolvimento de Software",
                    LocalDate.of(2023, 1, 1), LocalDate.of(2023, 6, 30), null);
            Projeto projeto2 = new Projeto(null, "Campanha Publicitária",
                    LocalDate.of(2023, 3, 1), LocalDate.of(2023, 8, 31), null);
            projeto1 = projetoRepository.save(projeto1);
            projeto2 = projetoRepository.save(projeto2);

            
            Funcionario funcionario1 = new Funcionario(null, "João Silva", setor1, List.of(projeto1, projeto2));
            Funcionario funcionario2 = new Funcionario(null, "Maria Souza", setor1, List.of(projeto1));
            Funcionario funcionario3 = new Funcionario(null, "Ana Costa", setor2, List.of(projeto2));
            funcionarioRepository.save(funcionario1);
            funcionarioRepository.save(funcionario2);
            funcionarioRepository.save(funcionario3);

            System.out.println("\n*** FUNCIONÁRIOS POR SETOR ***");
            setorRepository.findAllWithFuncionarios().forEach(setor -> {
                System.out.println("Setor: " + setor.getNome());
                setor.getFuncionarios().forEach(funcionario -> {
                    System.out.println("  - " + funcionario.getNome());

                });
            });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Ac2Application.class, args);
    }
}
