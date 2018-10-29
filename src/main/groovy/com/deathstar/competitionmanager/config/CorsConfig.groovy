package com.deathstar.competitionmanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class CorsConfig {

    @Value('${cm.cors.ui-host:http://localhost:4200}')
    String uiHost

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            void addCorsMappings(CorsRegistry registry) {
                registry.addMapping('/**').allowedOrigins(uiHost)
            }
        }
    }
}
