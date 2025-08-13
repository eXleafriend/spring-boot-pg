package com.example.springbootpg.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringUtils {

	public static String escapeLike(final String value) {
		if (value == null) {
			return null;
		}
		return value.replace("%", "\\%").replace("_", "\\_");
	}

}
