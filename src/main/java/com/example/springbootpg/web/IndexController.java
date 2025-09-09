package com.example.springbootpg.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootpg.ArithmeticService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IndexController {

	private final ArithmeticService arithmeticService;

	@GetMapping
	public Map<String, Object> index() {
		arithmeticService.nothing();
		return Map.of("epochTime", new Date().getTime());
	}

	@GetMapping("/sum")
	public Map<String, Integer> sum(@RequestParam(required = false) List<Integer> operands) {
		return Map.of("result", arithmeticService.sum(operands));
	}

}
