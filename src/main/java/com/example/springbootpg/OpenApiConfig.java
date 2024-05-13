package com.example.springbootpg;

import org.springdoc.core.GroupedOpenApi;
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

	@Bean
	public GroupedOpenApi v1() {
		String pathsToMatch[] = { "/v1/**" };
		return GroupedOpenApi.builder()
				.group("v1")
				.pathsToMatch(pathsToMatch)
				.build();
	}

	@Bean
	public GroupedOpenApi v2() {
		String pathsToMatch[] = { "/v2/**" };
		return GroupedOpenApi.builder()
				.group("v2")
				.pathsToMatch(pathsToMatch)
				.build();
	}

}
