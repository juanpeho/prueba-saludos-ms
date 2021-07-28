package com.saludos.pruebasaludosms.domain.model;

public class Saludo {
    
    private String id;
    private String saludoCompuesto;

    public Saludo(String id, String saludoCompuesto) {
        this.id = id;
        this.saludoCompuesto = saludoCompuesto;
    }

    public String getId() {
        return id;
    }

    public String getSaludoCompuesto() {
        return saludoCompuesto;
    }
}
