package com.example.springbootpg.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepo extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

}
