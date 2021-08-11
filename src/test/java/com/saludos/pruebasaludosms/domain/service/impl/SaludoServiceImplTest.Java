package com.saludos.pruebasaludosms.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import com.saludos.pruebasaludosms.domain.model.Saludo;
import com.saludos.pruebasaludosms.domain.repository.SaludoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SaludoServiceImpl.class})
class SaludoServiceImplTest {

    private String TIPO_ID = "CC";
    private String NUMERO_ID = "123456789";
    private String NOMBRE = "Nombre Apellido";
    
    @Autowired
    private SaludoServiceImpl service;

    @MockBean
    private SaludoRepository repository;

    @Test
    void whenSaludar_thenSaludo() {
        Mockito.doNothing().when(repository).save(Mockito.any());

        String saludoResponse = service.saludar(TIPO_ID, NUMERO_ID, NOMBRE);

        assertNotNull(saludoResponse);
        assertEquals("Te saludo a ti Nombre Apellido, con ID: CC123456789", saludoResponse);
    }

    @Test
    void whenConsultarSaludoExistente_thenSaludo() {
        Saludo saludo = new Saludo(TIPO_ID + NUMERO_ID, "Te saludo a ti " + NOMBRE + ", con ID: " + TIPO_ID + NUMERO_ID);

        Mockito.when(repository.findById(TIPO_ID + NUMERO_ID)).thenReturn(Optional.of(saludo));

        String saludoResponse = service.consultarSaludo(TIPO_ID, NUMERO_ID);

        assertNotNull(saludoResponse);
        assertEquals("Te saludo a ti Nombre Apellido, con ID: CC123456789", saludoResponse);
    }

    @Test
    void whenConsultarSaludoNoExistente_thenSaludoNoExiste() {
        Mockito.when(repository.findById(TIPO_ID + NUMERO_ID)).thenReturn(Optional.empty());

        String saludoResponse = service.consultarSaludo(TIPO_ID, NUMERO_ID);

        assertNotNull(saludoResponse);
        assertEquals("No existe saludo con ID: CC123456789", saludoResponse);
    }

    @Test
    void whenConstruirSaludo_thenSaludo() {
        Saludo saludoResponse = service.construirSaludo(TIPO_ID, NUMERO_ID, NOMBRE);

        assertNotNull(saludoResponse);
        assertEquals("CC123456789", saludoResponse.getId());
        assertEquals("Te saludo a ti Nombre Apellido, con ID: CC123456789", saludoResponse.getSaludoCompuesto());
    }
}
