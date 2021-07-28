package com.saludos.pruebasaludosms.domain.service;

public interface SaludoService {

    String saludar(String tipoId, String numeroId, String nombre);
    String consultarSaludo(String tipoId, String numeroId);
}