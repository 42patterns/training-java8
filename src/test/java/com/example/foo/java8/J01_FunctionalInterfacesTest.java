package com.example.foo.java8;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * What is a functional interface? @FunctionalInterface
 * Using lambdas instead of plain old Java classes (JButton)
 * - Implement functional interfaces using lambda syntax
 */
@Ignore
public class J01_FunctionalInterfacesTest {

	private final Random random = new Random();

	@Test
	public void testActionListenerLambda() {
		//given
		final Date dateMock = mock(Date.class);
		final ActionListener listener = null;

		//when
		listener.actionPerformed(null);

		//then
		verify(dateMock).setTime(1000L);
	}

	@Test
	public void testRunnableLambda() {
		//given
		final Date dateMock = mock(Date.class);
		Runnable block = null;

		//when
		block.run();

		//then
		verify(dateMock).setTime(1000L);
	}

	@Test
	public void testComparatorLambda() {
		final Comparator<String> stringLengthComparator = null;

		assertThat(stringLengthComparator.compare("abc", "def")).isZero();
		assertThat(stringLengthComparator.compare("abc", "defg")).isLessThan(0);
		assertThat(stringLengthComparator.compare("abc", "de")).isGreaterThan(0);
	}

	@Test
	public void testCustomFunctionalInterface() {
		final RandomSource source = null;

		Supplier<Integer> sourceSupplier = null;

		assertThat(source.oneOrMinusOne()).isIn(-1, 1);
		assertThat(sourceSupplier.get()).isIn(-1, 1);
	}

}

