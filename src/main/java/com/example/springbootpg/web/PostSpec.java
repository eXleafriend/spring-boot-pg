package com.example.springbootpg.web;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.BindParam;

import com.example.springbootpg.domain.Post;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Value
public class PostSpec implements Specification<Post> {

	private String title;

	@BindParam("author.name")
	private String authorName;

	public PostSpec(final String title, final String authorName) {
		this.title = title;
		this.authorName = authorName;
	}

	@Override
	public Predicate toPredicate(@NonNull final Root<Post> root, @Nullable final CriteriaQuery<?> query,
			@NonNull final CriteriaBuilder cb) {

		var predicate = cb.conjunction();

		if (title != null && !title.isEmpty()) {
			predicate = cb.and(predicate, cb.like(root.get("title"), "%" + title + "%"));
		}

		if (authorName != null && !authorName.isEmpty()) {
			predicate = cb.and(predicate, cb.like(root.get("author").get("name"), "%" + authorName + "%"));
		}

		return predicate;

	}

}
