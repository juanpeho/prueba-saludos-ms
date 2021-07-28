package com.saludos.pruebasaludosms.domain.repository;

import java.util.Optional;
import com.saludos.pruebasaludosms.domain.model.Saludo;

public interface SaludoRepository {
    
    Optional<Saludo> findById(String id);

    void save(Saludo saludo);
}
