package com.example.ac2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.SetorRepository;


@Service
public class SetorServiceImpl implements SetorService {

    @Autowired
    private SetorRepository setorRepository;

    
    @Override
    public void adicionar(SetorDTO setorDTO) {
        Setor setor = Setor.builder()
                .nome(setorDTO.getNome())
                .build();

        setorRepository.save(setor);
    }

    
    @Override
    public SetorDTO buscarSetorPorId(Integer idSetor) {
        Setor setor = setorRepository.findById(idSetor)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        return SetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .build();
    }
}