package com.cosmos_api.Cosmos.API.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://cosmos-seven-delta.vercel.app/",
                        "http://localhost:3000",
                        "https://cosmosapi.up.railway.app/swagger-ui")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
