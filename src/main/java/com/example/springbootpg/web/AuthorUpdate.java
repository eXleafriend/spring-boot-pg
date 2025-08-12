package com.example.springbootpg.web;

import com.example.springbootpg.domain.Author;

public record AuthorUpdate(String name) {

	public void updateEntity(final Author author) {
		author.setName(name);
	}

}
