package com.saludos.pruebasaludosms.infrastructure.repository;

import java.util.Optional;

import com.saludos.pruebasaludosms.domain.model.Saludo;
import com.saludos.pruebasaludosms.domain.repository.SaludoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDBSaludoRepositoryImpl implements SaludoRepository {

    private final SpringDataMongoSaludoRepository saludoRepository;

    @Autowired
    public MongoDBSaludoRepositoryImpl(final SpringDataMongoSaludoRepository saludoRepository) {
        this.saludoRepository = saludoRepository;
    }

    @Override
    public Optional<Saludo> findById(final String id) {
        return saludoRepository.findById(id);
    }

    @Override
    public void save(final Saludo saludo) {
        saludoRepository.save(saludo);        
    }
    
}
