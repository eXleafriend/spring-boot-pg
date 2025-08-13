package com.example.springbootpg.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA
@Entity
// Lombok
@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Post {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private final Long id = null;

	@ManyToOne(optional = false)
	@JoinColumn(name = "author_id", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_post_author_id"))
	private Author author;

	@Column(nullable = false)
	private final Date postedAt = new Date();

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

}
