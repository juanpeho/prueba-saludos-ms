package com.saludos.pruebasaludosms.infrastructure.repository;

import com.saludos.pruebasaludosms.domain.model.Saludo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoSaludoRepository extends MongoRepository<Saludo, String> {    
}
