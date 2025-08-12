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

import com.example.springbootpg.domain.Author;
import com.example.springbootpg.domain.AuthorRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorRepo authorRepo;

	@GetMapping("")
	public Page<Author> listAuthors(@RequestParam(required = false) final String name,
			@PageableDefault(10) final Pageable pageable) {

		Specification<Author> spec = Specification.unrestricted();

		if (name != null && !name.isEmpty()) {
			spec = spec.and((root, query, cb) -> {
				return cb.like(root.get("name"), "%" + name + "%");
			});
		}

		final var page = authorRepo.findAll(spec, pageable);
		return page;

	}

	@PostMapping("")
	public Author registerAuthor(@RequestBody final AuthorRegistration registration) {

		final var author = registration.toEntity();
		authorRepo.save(author);
		return author;

	}

	@PatchMapping("/{username}")
	public Author updateAuthor(@PathVariable final String username,
			@RequestBody final AuthorUpdate update) {

		final var author = authorRepo.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No author found - username: " + username));

		update.updateEntity(author);

		return author;

	}

	@DeleteMapping("/{username}")
	public void unregisterAuthor(@PathVariable final String username,
			@RequestBody final AuthorRegistration registration) {

		authorRepo.findByUsername(username)
				.map(Author::getId)
				.ifPresent(authorRepo::deleteById);

	}

}
