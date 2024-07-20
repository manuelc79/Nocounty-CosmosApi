package com.cosmos_api.Cosmos.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CosmosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmosApiApplication.class, args);
	}


}
