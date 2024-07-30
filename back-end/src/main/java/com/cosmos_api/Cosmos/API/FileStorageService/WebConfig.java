package com.cosmos_api.Cosmos.API.FileStorageService;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
      @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/home/roman/no_contry/s16_03_m_Java_React/s16-03-m-Java-React/back-end/src/main/java/com/cosmos_api/Cosmos/API/FileStorageService/upload/");
    }
}
