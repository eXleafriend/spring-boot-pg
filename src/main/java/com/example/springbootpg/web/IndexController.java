package com.example.springbootpg.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.DummyInvocationUtils;
import org.springframework.hateoas.server.core.LastInvocationAware;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping
@RestController
public class IndexController {

	@GetMapping("/")
	@Operation(summary = "Shows epoch time")
	@Linked(title = "API Index")
	public EntityModel<IndexResult> index() {
		final var epocheTime = System.currentTimeMillis();
		final var content = new IndexResult(epocheTime);
		return EntityModel.of(content,
				titledLinkTo(methodOn(IndexController.class).index()));
	}

	@GetMapping("/ping")
	public EntityModel<Map<String, String>> ping() {
		return EntityModel.of(Map.of("message", "pong"),
				titledLinkTo(methodOn(IndexController.class).ping()));
	}

	public Link titledLinkTo(final Object invocationValue) {

		LastInvocationAware invocations = (LastInvocationAware) DummyInvocationUtils
				.getLastInvocationAware(invocationValue);

		if (invocations == null) {
			throw new IllegalStateException(
					String.format("Could not obtain previous invocation from %s!", invocationValue));
		}

		final var invocation = invocations.getLastInvocation();
		final var method = invocation.getMethod();
		final var linked = method.getAnnotation(Linked.class);
		final var title = linked == null ? null : linked.title();

		return WebMvcLinkBuilder.linkTo(invocationValue).withSelfRel().withTitle(title);
	}

}
