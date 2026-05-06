package com.example.ac2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    
    @PostMapping
    public void adicionar(@RequestBody SetorDTO setorDTO) {
        setorService.adicionar(setorDTO);  
    }

    
    @GetMapping("/{idSetor}")
    public SetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
        return setorService.buscarSetorPorId(idSetor);  

    }
}
