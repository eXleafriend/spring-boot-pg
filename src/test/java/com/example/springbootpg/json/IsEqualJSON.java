package com.example.springbootpg.json;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

/**
 * A Matcher for comparing JSON.
 * Example usage:
 *
 * <pre>
 * assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo\", \"bar\"]"));
 * assertThat(new String[] { "foo", "bar" }, equalToJSONInFile("/tmp/foo.json"));
 * </pre>
 *
 * Source: https://www.javacodegeeks.com/2018/03/junit-hamcrest-matcher-for-json.html
 */
public class IsEqualJSON extends DiagnosingMatcher<Object> {

	private final String expectedJSON;
	private JSONCompareMode jsonCompareMode;

	public IsEqualJSON(final String expectedJSON) {
		this.expectedJSON = expectedJSON;
		this.jsonCompareMode = JSONCompareMode.STRICT;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText(expectedJSON);
	}

	@Override
	protected boolean matches(final Object actual,
			final Description mismatchDescription) {
		final String actualJSON = toJSONString(actual);

		try {
			JSONCompareResult result = JSONCompare.compareJSON(expectedJSON,
					actualJSON,
					jsonCompareMode);
			if (!result.passed()) {
				mismatchDescription.appendText(result.getMessage());
			}
			return result.passed();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private static String toJSONString(final Object o) {
		try {
			return o instanceof String ? (String) o : new ObjectMapper().writeValueAsString(o);
		} catch (final JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFileContents(final Path path) {
		try {
			return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static IsEqualJSON equalToJSON(final String expectedJSON) {
		return new IsEqualJSON(expectedJSON);
	}

	public static IsEqualJSON equalToJSONInFile(final Path expectedPath) {
		return equalToJSON(getFileContents(expectedPath));
	}

	public static IsEqualJSON equalToJSONInFile(final String expectedFileName) {
		return equalToJSONInFile(Paths.get(expectedFileName));
	}

}
