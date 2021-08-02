package com.saludos.pruebasaludosms.application.restcontrollers;

import javax.validation.Valid;

import com.saludos.pruebasaludosms.domain.service.SaludoService;
import com.saludos.pruebasaludosms.application.request.SaludoRequest;
import com.saludos.pruebasaludosms.application.response.SaludoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/saludos")
public class SaludoController {
    
    private SaludoService saludoService;
    
    @Autowired
    public SaludoController(SaludoService service) {
        this.saludoService = service;
    }

    @Operation(summary = "Genera un saludo", description = "Construye y almacena el saludo para los parametros ingresados.", tags = {"Saludo"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok")
    })
    @PostMapping("/saludar")
    public ResponseEntity<SaludoResponse> saludar(@Valid @RequestBody SaludoRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new SaludoResponse(saludoService.saludar(request.getTipoId(), request.getNumeroId(), request.getNombre())));
    }

    @Operation(summary = "Consulta el saludo", description = "Consulta el saludo para la identificación ingresada.", tags = {"Saludo"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/consultar/{tipoId}/{numeroId}")    
    public ResponseEntity<SaludoResponse> consultarSaludo(@PathVariable final String tipoId, @PathVariable final String numeroId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new SaludoResponse(saludoService.consultarSaludo(tipoId, numeroId)));
    }

    @Operation(summary = "Ping the app", description = "Ping the app", tags = {"Saludo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")})
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
