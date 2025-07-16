package com.example.springbootpg.domain;

import java.util.Optional;

import org.jinq.orm.stream.JinqStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

	private final PostRepo postRepo;

	public Post savePost(Post post) {
		return postRepo.save(post);
	}

	public Page<Post> getAllPosts(final Pageable pageable) {

		final var pageNumber = pageable.getPageNumber();
		final var pageSize = pageable.getPageSize();

		final var content = stream()
				.sortedDescendingBy(Post::getId) // Sort by ID in descending order
				.skip(pageNumber * pageSize)
				.limit(pageSize)
				.toList();

		final var total = stream().count();
		final var pageImpl = new PageImpl<>(content, PageRequest.of(pageNumber, pageSize), total);
		return pageImpl;

	}

	private JinqStream<Post> stream() {
		return postRepo.stream();
	}

	public Optional<Post> findPostById(Long id) {
		return postRepo.findById(id);
	}

	public void deletePost(Post post) {
		postRepo.remove(post);
	}
}
