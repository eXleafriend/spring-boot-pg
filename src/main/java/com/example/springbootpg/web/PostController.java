package com.example.springbootpg.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.springbootpg.domain.Post;
import com.example.springbootpg.domain.PostService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	private final HttpHeaders headers = new HttpHeaders();

	private final byte[] body = new byte[0];

	@GetMapping("")
	public Page<Post> listPosts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return postService.getAllPosts(pageable);
	}

	@GetMapping("/{id}")
	public Post viewPost(@PathVariable long id) {
		return postService.findPostById(id)
				.orElseThrow(() -> HttpClientErrorException.create("Post not found with id: " + id,
						HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), headers, body, null));
	}

	@PostMapping("")
	public Post savePost(@RequestBody Post entity) {
		if (entity.getTitle() == null || entity.getContent() == null) {
			throw HttpClientErrorException.create("Title and content must not be null",
					HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), headers, body, null);
		}
		return postService.savePost(entity);
	}

	@DeleteMapping("/{id}")
	public Post deletePost(@PathVariable long id) {
		final var entity = viewPost(id);
		postService.deletePost(entity);
		return entity;
	}

}
