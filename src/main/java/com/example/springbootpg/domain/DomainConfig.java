package com.example.springbootpg.domain;

import java.util.Optional;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class DomainConfig {

	@Bean
	PostRepo postRepo(final EntityManager entityManager) {

		final var entityManagerFactory = entityManager.getEntityManagerFactory();
		JinqJPAStreamProvider jinqStreamProvider = new JinqJPAStreamProvider(entityManagerFactory);
		return new PostRepo() {

			@Override
			public Post save(Post entity) {
				entityManager.persist(entity);
				return entity;
			}

			@Override
			public Optional<Post> findById(Long id) {
				final var post = entityManager.find(Post.class, id);
				return Optional.ofNullable(post);
			}

			@Override
			public JinqStream<Post> stream() {
				return jinqStreamProvider.streamAll(entityManager, Post.class);
			}

			@Override
			public void remove(Post entity) {
				if (entityManager.contains(entity)) {
					entityManager.remove(entity);
				} else {
					final var managedEntity = entityManager.find(Post.class, entity.getId());
					if (managedEntity != null) {
						entityManager.remove(managedEntity);
					}
				}
			}

		};
	}

}
