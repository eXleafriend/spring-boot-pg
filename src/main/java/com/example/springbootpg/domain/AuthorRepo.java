package com.example.springbootpg.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepo extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

}
