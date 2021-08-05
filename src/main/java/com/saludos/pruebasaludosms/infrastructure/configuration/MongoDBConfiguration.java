package com.saludos.pruebasaludosms.infrastructure.configuration;

import com.saludos.pruebasaludosms.infrastructure.repository.SpringDataMongoSaludoRepository;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoSaludoRepository.class)
public class MongoDBConfiguration {    
}