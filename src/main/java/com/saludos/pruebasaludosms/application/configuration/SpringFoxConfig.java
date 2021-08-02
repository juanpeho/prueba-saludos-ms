package com.saludos.pruebasaludosms.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {
    
    private static final String TITLE = "Prueba Saludos Rest API";
    private static final String DESCRIPTION = "Saluda y almacena el saludo";
    private static final String VERSION = "Beta 0.0.1";
    private static final String AUTHOR = "Juan Camilo Pérez";
    private static final String EMAIL = "Juan Camilo Pérez";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiDetails())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.saludos.pruebasaludosms.application.restcontrollers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiDetails() {
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                "",
                new Contact(AUTHOR, "", EMAIL),
                "",
                "",
                Collections.emptyList());
    }
}
