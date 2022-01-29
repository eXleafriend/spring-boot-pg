package com.example.springbootpg.web;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.springbootpg.web.response.DataResponse;
import com.example.springbootpg.web.response.Error;
import com.example.springbootpg.web.response.ErrorResponse;
import com.example.springbootpg.web.response.Response;
import com.example.springbootpg.web.response.Warning;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
		return new DataResponse<>(userRecord);
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
		return new DataResponse<>(userDto);
	}

	@PostMapping()
	@ResponseBody
	public Response<UserDto> register(@Valid @RequestBody final UserDto userDto, final BindingResult bindingResult) {
		$("""
				cat | http -v POST localhost:8080/user
				{
				  "name": "leafriend",
				  "email": "leafriend"
				}
				""");
		final var warnings = ((Supplier<List<Warning<?>>>)( () -> {
			if (userDto.getEmail() == null) {
				return List.of(new Warning<>("E007", "Null email is danger", null));
			} else {
				return null;
			}
		})).get();
		if (bindingResult.hasErrors()) {
			final var errors = bindingResult.getAllErrors().stream().map(error -> new Error<>("H400", "Bad Request", error)).collect(Collectors.toList());
			return new ErrorResponse<UserDto>(errors, warnings);
		}
		return new DataResponse<>(userDto, warnings);
	}

}
