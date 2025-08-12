package com.example.springbootpg.web;

import com.example.springbootpg.domain.Post;

public record PostUpdate(String title, String content) {

	public void updateEntity(final Post post) {
		post.setTitle(title);
		post.setContent(content);
	}

}
