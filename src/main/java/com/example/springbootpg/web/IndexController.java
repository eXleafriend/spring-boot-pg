package com.example.springbootpg.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping
	@ResponseBody
	public Map<String, Object> index() {
		return Map.of("epochTime", new Date().getTime());
	}

}
