package com.example.springbootpg.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.DummyInvocationUtils;
import org.springframework.hateoas.server.core.LastInvocationAware;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;

@RequestMapping
@RestController
public class IndexController {

	private String message = "pong";

	@GetMapping("/")
	@Operation(summary = "Shows epoch time")
	@Linked(title = "API Index")
	public EntityModel<IndexResult> index() {
		final var epocheTime = System.currentTimeMillis();
		final var content = new IndexResult(epocheTime);
		return EntityModel.of(content,
				titledLinkTo(methodOn(IndexController.class).index()));
	}

	@RequestMapping(value = "/ping", method = RequestMethod.OPTIONS)
	public ResponseEntity<Void> $ping() {
		return ResponseEntity.ok().header("Allow", "OPTIONS, GET, PUT").build();
	}

	@GetMapping("/ping")
	public EntityModel<Map<String, String>> ping() {
		return EntityModel.of(Map.of("message", message),
				titledLinkTo(methodOn(IndexController.class).ping())
						.andAffordance(afford(methodOn(IndexController.class).$ping()))
						.andAffordance(afford(methodOn(IndexController.class).message(null))) //
		);
	}

	@Data
	public static class Message {
		private String message;
	}

	@PutMapping("/ping")
	public EntityModel<Message> message(@RequestBody Message message) {
		this.message = message.getMessage();
		return EntityModel.of(message,
				titledLinkTo(methodOn(IndexController.class).ping())
						.andAffordance(afford(methodOn(IndexController.class).$ping()))
						.andAffordance(afford(methodOn(IndexController.class).ping())) //
		);
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
