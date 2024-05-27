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
		final var model = controller.index();
		final var result = model.getContent();

		// Assert
		Thread.sleep(1);
		final var after = System.currentTimeMillis();
		@SuppressWarnings("null")
		final var epochTime = result.getEpochTime();
		assertThat(epochTime, allOf(greaterThan(before), lessThan(after)));

	}

}
