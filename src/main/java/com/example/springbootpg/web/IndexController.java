package com.example.springbootpg.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping
	@ResponseBody
	public Response<EpochTime> index() {
		final var data = new EpochTime(new Date().getTime());
		return new Response<>(data);
	}

	public static record EpochTime(long epochTime) {
	}

}
