package com.example.springbootpg.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// JPA
@Entity
// Lombok
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Author {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private final Long id;

	@Column(nullable = false)
	private final Date registeredAt = new Date();

	@Column(unique = true, nullable = false)
	private final String username;

	@Column(nullable = false)
	private String name;

	protected Author() {
		this.id = null;
		this.username = null;
	}

	public Author(final Long id) {
		this.id = id;
		this.username = null;
		this.name = null;
	}

	public Author(final String username, final String name) {
		this.id = null;
		this.username = username;
		this.name = name;
	}

}
