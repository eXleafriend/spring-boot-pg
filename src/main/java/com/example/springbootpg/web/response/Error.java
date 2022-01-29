package com.example.springbootpg.web.response;

import lombok.Value;

@Value
public class Error<E> {
	private final String coce;
	private final String message;
	private final E source;
}
