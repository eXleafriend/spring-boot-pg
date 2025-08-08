package com.example.springbootpg.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// JPA
@Entity
// Lombok
@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Author {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private final Long id = null;

	@Column(nullable = false)
	private final Date registeredAt = new Date();

	@Column(unique = true, nullable = false)
	private final String username;

	@Column(nullable = false)
	private String name;

}
