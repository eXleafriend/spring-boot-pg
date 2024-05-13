package com.example.springbootpg.v2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class MainController {

	@GetMapping
	@Operation(
		summary = "Default API",
		description = """
				Returns map with current epoch time in millisecond.

				The map contains only `"epochTime"` entry.
				"""
	)
	@ResponseBody
	public Main main() {
		return new Main(System.currentTimeMillis());
	}

}
