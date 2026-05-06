package com.example.ac2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Método para adicionar um projeto
    @Override
    public void adicionar(ProjetoDTO projetoDTO) {
        Projeto projeto = Projeto.builder()
                .descricao(projetoDTO.getDescricao())
                .dataInicio(projetoDTO.getDataInicio())
                .dataFim(projetoDTO.getDataFim())
                .build();

        projetoRepository.save(projeto);
    }

    // Método para buscar um projeto por ID com a lista de funcionários
    @Override
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(projeto.getFuncionarios().stream()
                        .map(funcionario -> FuncionarioDTO.builder()
                                .id(funcionario.getId())
                                .nome(funcionario.getNome())
                                .build())
                        .toList())
                .build();
    }

    // Método para vincular um funcionário a um projeto
    @Override
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
}