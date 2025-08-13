package com.example.springbootpg.web;

import com.example.springbootpg.domain.Author;
import com.example.springbootpg.domain.Post;

public record PostRegistration(long authorId, String title, String content) {

	public Post toEntity() {
		final var post = new Post(new Author(authorId), title, content);
		return post;
	}

}
