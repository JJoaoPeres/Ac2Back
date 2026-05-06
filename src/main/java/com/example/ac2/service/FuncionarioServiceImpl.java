package com.example.ac2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    // Método para adicionar um funcionário
    @Override
    public void adicionar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioDTO.getNome())
                .build();

        funcionarioRepository.save(funcionario);
    }

    // Método para buscar todos os projetos de um funcionário
    @Override
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        if (!funcionario.isPresent()) {
            throw new RegraNegocioException("Funcionário não encontrado");
        }

        List<Projeto> projetos = funcionario.get().getProjetos();
        return projetos.stream()
                .map(projeto -> DadosProjetoDTO.builder()
                        .id(projeto.getId())
                        .descricao(projeto.getDescricao())
                        .dataInicio(projeto.getDataInicio())
                        .dataFim(projeto.getDataFim())
                        .build())
                .collect(Collectors.toList());
    }
}