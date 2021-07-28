package com.saludos.pruebasaludosms.application.restcontrollers;

import com.saludos.pruebasaludosms.domain.service.SaludoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludos")
public class SaludoController {
    
    private final SaludoService saludoService;
    
    @Autowired
    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    
}
