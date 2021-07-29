package com.saludos.pruebasaludosms.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaludoResponse {

    @JsonProperty("saludo_retorno")
    private String saludo;

    public SaludoResponse(String saludo) {
        this.saludo = saludo;
    }

    public String getSaludo() {
        return saludo;
    }
}
