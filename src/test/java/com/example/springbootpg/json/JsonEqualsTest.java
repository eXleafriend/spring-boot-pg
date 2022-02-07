package com.example.springbootpg.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.example.springbootpg.json.IsEqualJSON.equalToJSON;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonEqualsTest {

	private String expected = """
			{
				"friends": [
					{
						"name": "Corby Page",
						"id": 123
					},
					{
						"id": 456,
						"name": "Carter Page"
					}
				]
			}
			""";

	private String data = """
			{
				friends: [
					{
						id: 123,
						name: "Corby Page"
					},
					{
						id: 456,
						name: "Carter Page"
					}
				]
			}
			""";
	private String wrongData = """
			{
				friends: [
					{
						id: 123,
						name: "Corby Page"
					},
					{
						id: 789,
						name: "Carter Page"
					}
				]
			}
			""";

	@Test
	public void testViaAssertEquals() throws JSONException {
		JSONAssert.assertEquals(expected, data, false);
	}

	@Test
	public void testViaAssertThat() throws JSONException {
		assertThat(data, is(equalToJSON(expected)));
		assertThat(wrongData, is(not(equalToJSON(expected))));
	}

}
