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
public final class DataResponse<T> extends Response<T> {

	private final T data;

	@JsonInclude(Include.NON_NULL)
	private final List<? extends Warning<?>> warnings;

	public DataResponse(final T data) {
		this(data, null);
	}

}
