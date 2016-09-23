package com.example.foo.java8;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @see Map#merge(Object, Object, BiFunction)
 * @see Map#computeIfAbsent(Object, Function)
 * @see Collectors#groupingBy(Function)
 */
public class LoremIpsum {

	public static String text() throws IOException {
		return IOUtils.toString(LoremIpsum.class.getResourceAsStream("/lorem-ipsum.txt"));
	}

	/**
	 * Case insensitive
	 */
	public static Map<String, Integer> wordCount(String text) {
        final List<String> words = splitWords(text);
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.merge(word.toLowerCase(), 1, (x, one) -> x + one);
        }
        return wordCount;
	}

	private static List<String> splitWords(String text) {
        final String[] split = text.split("[ \\.,\n]");
        return Arrays.stream(split)
				.map(s -> s.trim())
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
	}

}
