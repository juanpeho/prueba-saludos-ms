package com.saludos.pruebasaludosms.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.saludos.pruebasaludosms.domain.model.Saludo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MongoDBSaludoRepositoryImplTest {

    final Saludo SALUDO = new Saludo("CC123456789", "Te saludo a ti Nombre Apellido, con ID: CC123456789");
    final String SALUDO_ID = "CC123456789";

    private SpringDataMongoSaludoRepository springDataMongoSaludoRepository;
    private MongoDBSaludoRepositoryImpl tested;

    @BeforeEach
    void setUp() {
        springDataMongoSaludoRepository = mock(SpringDataMongoSaludoRepository.class);
        tested = new MongoDBSaludoRepositoryImpl(springDataMongoSaludoRepository);
    }

    @Test
    void whenFindById_thenReturnSaludo() {        
        when(springDataMongoSaludoRepository.findById(SALUDO_ID)).thenReturn(Optional.of(SALUDO));

        Optional<Saludo> result = tested.findById(SALUDO_ID);

        assertEquals(SALUDO, result.get());
        assertEquals(SALUDO_ID, result.get().getId());
    }

    @Test
    void whenFindById_thenReturnEmpty() {        
        when(springDataMongoSaludoRepository.findById(SALUDO_ID)).thenReturn(Optional.empty());

        Optional<Saludo> result = tested.findById(SALUDO_ID);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void whenSave_thenSave() {
        
        tested.save(SALUDO);
        
        verify(springDataMongoSaludoRepository).save(SALUDO);
    }
}
