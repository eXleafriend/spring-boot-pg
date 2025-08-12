package com.example.springbootpg.web;

import com.example.springbootpg.domain.Author;

public record AuthorRegistration(String username, String name) {

	public Author toEntity() {
		final var author = new Author(username, name);
		return author;
	}

}
