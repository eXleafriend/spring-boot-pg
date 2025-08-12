package com.example.springbootpg.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootpg.domain.Post;
import com.example.springbootpg.domain.PostRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostRepo postRepo;

	@GetMapping("")
	public Page<Post> listPosts(@RequestParam(required = false) final String title,
			@RequestParam(name = "author.name", required = false) final String authorName,
			@PageableDefault(10) final Pageable pageable) {

		Specification<Post> spec = Specification.unrestricted();

		if (title != null && !title.isEmpty()) {
			spec = spec.and((root, query, cb) -> {
				return cb.like(root.get("title"), "%" + title + "%");
			});
		}

		if (authorName != null && !authorName.isEmpty()) {
			spec = spec.and((root, query, cb) -> {
				return cb.like(root.get("author").get("name"), "%" + title + "%");
			});
		}

		final var page = postRepo.findAll(spec, pageable);
		return page;

	}

	@PostMapping("")
	public Post registerPost(@RequestBody final PostRegistration registration) {

		final var post = registration.toEntity();
		postRepo.save(post);
		return post;

	}

	@PatchMapping("/{id}")
	public Post updatePost(@PathVariable final long id,
			@RequestBody final PostUpdate update) {

		final var post = postRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No post found - id: " + id));

		update.updateEntity(post);

		return post;

	}

	@DeleteMapping("/{id}")
	public void unregisterPost(@PathVariable final long id,
			@RequestBody final PostRegistration registration) {

		postRepo.deleteById(id);

	}

}
