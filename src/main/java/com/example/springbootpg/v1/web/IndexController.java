package com.example.springbootpg.v1.web;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class IndexController {

	@GetMapping
	@Operation(
		summary = "Default API",
		description = """
				Returns map with current epoch time in millisecond.

				The map contains only `"epochTime"` entry.
				"""
	)
	@ResponseBody
	public Map<String, Object> index() {
		return Map.of("epochTime", new Date().getTime());
	}

}
