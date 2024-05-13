package com.example.springbootpg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		final var info = new Info()
				.title("OpenAPI Example")
				.description("""
						OpenAPI on Spring Boot Example
						""")
				.version("0.1");
		return new OpenAPI()
				.info(info);
	}

}
