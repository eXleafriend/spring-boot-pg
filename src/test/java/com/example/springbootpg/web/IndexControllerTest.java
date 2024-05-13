package com.example.springbootpg.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.Test;

public class IndexControllerTest {

	@Test
	void testIndex() throws InterruptedException {

		// Arrange
		final var controller = new IndexController();
		final var before = System.currentTimeMillis();
		Thread.sleep(1);

		// Act
		final var map = controller.index();

		// Assert
		Thread.sleep(1);
		final var after = System.currentTimeMillis();
		final var epochTime = (Long) map.get("epochTime");
		assertThat(epochTime, allOf(greaterThan(before), lessThan(after)));

	}

}
