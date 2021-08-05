package com.saludos.pruebasaludosms.domain.service.impl;

import com.saludos.pruebasaludosms.domain.model.Saludo;
import com.saludos.pruebasaludosms.domain.repository.SaludoRepository;
import com.saludos.pruebasaludosms.domain.service.SaludoService;

import org.springframework.beans.factory.annotation.Autowired;

public class SaludoServiceImpl implements SaludoService {

    private final SaludoRepository repository;

    @Autowired
    public SaludoServiceImpl(SaludoRepository saludoRepository) {
        this.repository = saludoRepository;
    }

    @Override
    public String saludar(String tipoId, String numeroId, String nombre) {
        var saludo = construirSaludo(tipoId, numeroId, nombre);
        repository.save(saludo);
        return saludo.getSaludoCompuesto();
    }

    @Override
    public String consultarSaludo(String tipoId, String numeroId) {
        return repository.findById(tipoId + numeroId)
                         .orElse(new Saludo("000", "No existe saludo con ID: " + tipoId + numeroId))
                         .getSaludoCompuesto();
    }

    private Saludo construirSaludo(String tipoId, String numeroId, String nombre) {
        return(new Saludo(tipoId + numeroId, "Te saludo a ti " + nombre + ", con ID: " + tipoId + numeroId));
    }
    
}
