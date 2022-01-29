package com.example.springbootpg.web.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public final class ErrorResponse<T> extends Response<T> {

	private final List<? extends Error<?>> errors;

	@JsonInclude(Include.NON_NULL)
	private final List<? extends Warning<?>> warnings;

	public ErrorResponse(List<? extends Error<?>> errors) {
		this(errors, null);
	}

}
