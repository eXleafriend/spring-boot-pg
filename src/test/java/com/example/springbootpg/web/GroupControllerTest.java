package com.example.springbootpg.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupControllerTest {

	private Validator validator;

	private ObjectMapper objectMapper;

	@BeforeAll
	public void setUp() {

		final var factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		objectMapper = new ObjectMapper();
	}

	private String $(final Object value) throws JsonProcessingException {
		return objectMapper.writeValueAsString(value);
	}

	// @Test
	public void test() throws JsonMappingException, JsonProcessingException {

		final var json = """
				{
					"id": {"foo": "bar"}
				}
				""".formatted();
		final var group = objectMapper.readValue(json, GroupController.Group.class);

		final var violations = validator.validate(group);
		assertThat(violations, is(not(empty())));

	}

	@Nested
	public class id_is_omitted {
		// private final String id;

		@Nested
		public class name_is_omitted {
			// private final String name;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						}
						""".formatted();
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_null {
			private final String name = null;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "name": %s
						}
						""".formatted($(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_empty_string {
			private final String name = "";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "name": %s
						}
						""".formatted( $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_given {
			private final String name = "Admin";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "name": %s
						}
						""".formatted($(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

	}

	@Nested
	public class id_is_null {
		private final String id = null;

		@Nested
		public class name_is_omitted {
			// private final String name;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s
						}
						""".formatted($(id));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_null {
			private final String name = null;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_empty_string {
			private final String name = "";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_given {
			private final String name = "Admin";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

	}

	@Nested
	public class id_is_empty_string {
		private final String id = "";

		@Nested
		public class name_is_omitted {
			// private final String name;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s
						}
						""".formatted($(id));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_null {
			private final String name = null;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_empty_string {
			private final String name = "";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_given {
			private final String name = "Admin";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

	}

	@Nested
	public class id_is_given {
		private final String id = "admin";

		@Nested
		public class name_is_omitted {
			// private final String name;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s
						}
						""".formatted($(id));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(empty()));

				assertThat(group.getId(), is(equalTo("admin")));
				assertThat(group.getName(), is(equalTo("admin")));

			}

		}

		@Nested
		public class name_is_null {
			private final String name = null;

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(not(empty())));

			}

		}

		@Nested
		public class name_is_empty_string {
			private final String name = "";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(empty()));

				assertThat(group.getId(), is(equalTo("admin")));
				assertThat(group.getName(), is(equalTo("")));

			}

		}

		@Nested
		public class name_is_given {
			private final String name = "Admin";

			@Test
			public void test() throws JsonMappingException, JsonProcessingException {

				final var json = """
						{
						  "id": %s,
						  "name": %s
						}
						""".formatted($(id), $(name));
				final var group = objectMapper.readValue(json, GroupController.Group.class);

				final var violations = validator.validate(group);
				assertThat(violations, is(empty()));

				assertThat(group.getId(), is(equalTo("admin")));
				assertThat(group.getName(), is(equalTo("Admin")));

			}

		}

	}

}
