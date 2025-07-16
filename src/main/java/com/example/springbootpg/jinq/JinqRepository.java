package com.example.springbootpg.jinq;

import java.util.Optional;

import org.jinq.orm.stream.JinqStream;

public interface JinqRepository<T, ID> {

	T save(T entity);

	Optional<T> findById(ID id);

	JinqStream<T> stream();

	void remove(T entity);

}
