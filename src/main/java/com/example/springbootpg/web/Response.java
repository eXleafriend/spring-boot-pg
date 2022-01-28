package com.example.springbootpg.web;

import lombok.Value;

@Value
public class Response<T> {

	private final T data;

}
