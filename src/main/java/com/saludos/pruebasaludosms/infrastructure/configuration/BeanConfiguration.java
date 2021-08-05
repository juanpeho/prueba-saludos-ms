package com.saludos.pruebasaludosms.infrastructure.configuration;

import com.saludos.pruebasaludosms.PruebaSaludosMsApplication;
import com.saludos.pruebasaludosms.domain.repository.SaludoRepository;
import com.saludos.pruebasaludosms.domain.service.SaludoService;
import com.saludos.pruebasaludosms.domain.service.impl.SaludoServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PruebaSaludosMsApplication.class)
public class BeanConfiguration {

    @Bean
    SaludoService saludoService(final SaludoRepository saludoRepository) {
        return new SaludoServiceImpl(saludoRepository);
    }
}
