package com.example.springbootpg.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig implements InitializingBean {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		final var module = new SimpleModule();
		module.addDeserializer(GroupController.Group.class, new GroupController.Deserializer());
		objectMapper.registerModule(module);
	}

}
