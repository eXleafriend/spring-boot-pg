package com.example.springbootpg.web.response;

import lombok.Value;

@Value
public  class Warning<W> {
	private final String coce;
	private final String message;
	private final W source;
}
