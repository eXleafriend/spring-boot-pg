package com.example.springbootpg.domain;

import com.example.springbootpg.jinq.JinqRepository;

public interface PostRepo  extends JinqRepository<Post, Long> {
	// Define custom query methods if needed

}
