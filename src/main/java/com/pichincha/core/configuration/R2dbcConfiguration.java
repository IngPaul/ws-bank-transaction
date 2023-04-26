package com.pichincha.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfiguration {
    // Aquí podrías agregar otras configuraciones necesarias para R2DBC
}