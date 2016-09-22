package com.example.foo.java8;

import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
public class J05b_StreamReduceTest {

	@Test
	public void shouldAddNumbersUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final int sum = input.stream().reduce(0, (acc, x) -> acc + x);

		//then
		assertThat(sum).isEqualTo(2 + 3 + 5 + 7);
	}

	@Test
	public void shouldConcatNumbers() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final String result = input
				.stream()
				.reduce(
						new StringBuilder(),
						(acc, x) -> acc.append(x),
						(sb1, sb2) -> sb1.append(sb2))
				.toString();

		//then
		assertThat(result).isEqualToIgnoringCase("2357");
	}

	@Test
	public void shouldFindMaxUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(4, 2, 6, 3, 8, 1);

		//when
		final int max = input.stream().reduce(0, (acc, x) -> (x > acc)?x:acc);

		//then
		assertThat(max).isEqualTo(8);
	}

	@Test
	public void shouldSimulateMapUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final List<Integer> doubledPrimes = input.stream()
				.reduce(Collections.emptyList(),
						(list, x) -> {
							List<Integer> resultList = new ArrayList<>(list);
							resultList.add(x * 2);
							return resultList;
						},
						(list1, list2) -> {
							List<Integer> resultList = new ArrayList<>(list1);
							resultList.addAll(list2);
							return resultList;
						});

		//then
		assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
	}

	@Test
	public void shouldSimulateFilterUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);

		//when
		final List<Integer> onlyEvenNumbers = input.stream()
				.reduce(Collections.emptyList(),
						(list, x) -> {
							List<Integer> resultList = new ArrayList<>(list);
							if (x % 2 == 0) {
								resultList.add(x);
							}
							return resultList;
						},
						(list1, list2) -> {
							List<Integer> resultList = new ArrayList<>(list1);
							resultList.addAll(list2);
							return resultList;
						}
				);

		//then
		assertThat(onlyEvenNumbers).containsExactly(2, 4, 6);
	}

}
