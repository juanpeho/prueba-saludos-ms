package com.saludos.pruebasaludosms.infrastructure.repository;

import java.util.Optional;

import com.saludos.pruebasaludosms.domain.model.Saludo;
import com.saludos.pruebasaludosms.domain.repository.SaludoRepository;

import org.springframework.stereotype.Repository;

@Repository
public class MongoDBSaludoRepositoryImpl implements SaludoRepository {

    @Override
    public Optional<Saludo> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Saludo saludo) {
        // TODO Auto-generated method stub
        
    }
    
}
