package com.saludos.pruebasaludosms.application.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaludoRequest {
    
    @JsonProperty
    @NotNull
    @NotEmpty
    private String tipoId;

    @JsonProperty
    @NotNull
    @NotEmpty
    private String numeroId;

    @JsonProperty
    @NotNull
    @NotEmpty
    private String nombre;

    public String getTipoId() {
        return tipoId;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public String getNombre() {
        return nombre;
    }
}
