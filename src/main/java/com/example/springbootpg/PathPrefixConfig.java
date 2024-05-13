package com.example.springbootpg;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathPrefixConfig implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/v1", HandlerTypePredicate.forBasePackage("com.example.springbootpg.v1.web"));
		configurer.addPathPrefix("/v2", HandlerTypePredicate.forBasePackage("com.example.springbootpg.v2"));
	}

}
