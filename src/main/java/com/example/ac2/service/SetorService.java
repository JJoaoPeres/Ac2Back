package com.example.ac2.service;

import com.example.ac2.dtos.SetorDTO;

public interface SetorService {

    public void adicionar(SetorDTO setorDTO);

    public SetorDTO buscarSetorPorId(Integer idSetor);

}
