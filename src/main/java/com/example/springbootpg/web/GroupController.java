package com.example.springbootpg.web;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Value
	@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
	@AllArgsConstructor
	public static class Group {

		@NotNull
		@NotEmpty
		private final String id;

		@NotNull
		private final String name;

		public Group(final String id) {
			this(id, id);
		}

	}

	public static class Deserializer extends StdDeserializer<GroupController.Group> {

		protected Deserializer() {
			super(Group.class);
		}

		private String getString(final JsonNode node) {
			if (node instanceof TextNode textNode) {
				return textNode.textValue();
			}
			return null;
		}

		@Override
		public GroupController.Group deserialize(final JsonParser jp, final DeserializationContext context)
				throws IOException, JacksonException {

			final JsonNode node = jp.getCodec().readTree(jp);

			final var idNode = node.get("id");
			final var id = getString(idNode);

			final var nameNode = node.get("name");
			final var name = getString(nameNode);
			// final var name = nameNode == null || nameNode instanceof NullNode ? null : nameNode.asText();

			if (nameNode != null) {
				return new GroupController.Group(id, name);
			} else {
				return new GroupController.Group(id);
			}


		}

	}

	@PostMapping
	@ResponseBody
	public Response<Group> register(@Valid @RequestBody final Group group) {
		return new Response<>(group);
	}

}
