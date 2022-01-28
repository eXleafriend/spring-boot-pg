package com.example.springbootpg.web;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@PostMapping
	@ResponseBody
	public Response<Group> register(@Valid @RequestBody final Group group) {
		return new Response<>(group);
	}

}
