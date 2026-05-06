package com.example.ac2.service;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.ProjetoDTO;

public interface ProjetoService {
    public void adicionar(ProjetoDTO projetoDTO);

    public DadosProjetoDTO buscarProjetoPorId(Integer id);

    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario);


}
