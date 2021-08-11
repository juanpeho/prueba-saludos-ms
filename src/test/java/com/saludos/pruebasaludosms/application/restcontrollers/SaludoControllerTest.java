package com.saludos.pruebasaludosms.application.restcontrollers;

import com.saludos.pruebasaludosms.domain.service.SaludoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    private String SALUDO_VALID_REQUEST = "{\"tipoId\":\"CC\",\"numeroId\":\"123456789\",\"nombre\":\"Juan Perez\"}";
    private String SALUDO_EMPTY_REQUEST = "{}";
    private String SALUDO_REQUEST_WITHOUT_TIPOID = "{\"numeroId\":\"123456789\",\"nombre\":\"Juan Perez\"}";
    private String SALUDO_REQUEST_WITHOUT_NUMEROID = "{\"tipoId\":\"CC\",\"nombre\":\"Juan Perez\"}";
    private String SALUDO_REQUEST_WITHOUT_NOMBRE = "{\"tipoId\":\"CC\",\"numeroId\":\"123456789\"}";
    private String SALUDO_RESPONSE = "Te saludo a ti Juan Perez, con ID: CC123456789"; 
    private String SALUDO_NOTFOUND_RESPONSE = "No existe saludo con ID: CC12345"; 

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaludoService saludoService;

    @Test
    void whenPostSaludar_withValidRequest_thenSucceedAndSaludar() throws Exception {
        Mockito.when(saludoService.saludar(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(SALUDO_RESPONSE);

        this.mockMvc.perform(post("/saludos/saludar")
                        .content(SALUDO_VALID_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.saludo_retorno").value(SALUDO_RESPONSE));
    }

    @Test
    void whenPostSaludar_withEmptyRequest_thenBadRequest() throws Exception {

        mockMvc.perform(post("/saludos/saludar")
                        .content(SALUDO_EMPTY_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostSaludar_withNoTipoId_thenBadRequest() throws Exception {

        mockMvc.perform(post("/saludos/saludar")
                        .content(SALUDO_REQUEST_WITHOUT_TIPOID)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostSaludar_withNoNumeroId_thenBadRequest() throws Exception {

        mockMvc.perform(post("/saludos/saludar")
                        .content(SALUDO_REQUEST_WITHOUT_NUMEROID)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostSaludar_withNoNombre_thenBadRequest() throws Exception {

        mockMvc.perform(post("/saludos/saludar")
                        .content(SALUDO_REQUEST_WITHOUT_NOMBRE)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenGetConsultarSaludo_withParameters_thenSucceed() throws Exception {
        Mockito.when(saludoService.consultarSaludo(Mockito.anyString(), Mockito.anyString())).thenReturn(SALUDO_RESPONSE);

        mockMvc.perform(get("/saludos/consultar/CC/123456789")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.saludo_retorno").value(SALUDO_RESPONSE));
    }

    @Test
    void whenGetConsultarSaludo_withParameters_thenSaludoNotFound() throws Exception {
        Mockito.when(saludoService.consultarSaludo(Mockito.anyString(), Mockito.anyString())).thenReturn(SALUDO_NOTFOUND_RESPONSE);

        mockMvc.perform(get("/saludos/consultar/CC/12345")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.saludo_retorno").value(SALUDO_NOTFOUND_RESPONSE));
    }

    @Test
    void whenGetConsultarSaludo_withNoParameters_thenNotFound() throws Exception {
        
        mockMvc.perform(get("/saludos/consultar")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void whenPing_thenSucceed() throws Exception {
        mockMvc.perform(get("/saludos/ping")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}