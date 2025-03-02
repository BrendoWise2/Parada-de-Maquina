package com.maquina.maquina.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir qualquer origem (para testes)
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5501") // Aqui você pode adicionar o frontend que está rodando
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Cabeçalhos permitidos
                .allowCredentials(true); // Se necessário, você pode permitir credenciais
    }
}

