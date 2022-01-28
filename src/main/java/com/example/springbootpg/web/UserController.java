package com.example.springbootpg.web;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Controller
@RequestMapping("/user")
public class UserController {

	public static record UserRecord(@NotNull String username, String name, boolean disabled) {

	}

	@Data
	public static class UserDto {

		@NotNull
		private String name;

		@Email
		private String email;
	}

	private void $(final String string) {
	}

	@PostMapping("/record")
	@ResponseBody
	public Response<UserRecord> registerRecord(@Valid @RequestBody final UserRecord userRecord) {
		$("""
				cat | http -v POST localhost:8080/user/record
				{
				  "username": "leafriend"
				}
				""");
		return new Response<>(userRecord);
	}

	@PostMapping("/dto")
	@ResponseBody
	public Response<UserDto> registerDto(@Valid @RequestBody final UserDto userDto) {
		$("""
				cat | http -v POST localhost:8080/user/dto
				{
				  "name": "leafriend",
				  "email": "leafriend"
				}
				""");
		return new Response<>(userDto);
	}

}
