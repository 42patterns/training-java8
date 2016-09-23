package com.example.foo.java8.funcset;

import java.util.function.Function;

/**
 * We represent a set by its characteristic function, i.e.
 * its `contains` predicate.
 *
 * This implementation is having purely functional nature,
 * but it's somehow unnatural for Java. It has a company
 * class Sets which aggregates all accompanying functions
 * (simlar to Array and Arrays, List and Lists,
 * File and Files, etc.)
 *
 */
@FunctionalInterface
public interface Set extends Function<Integer, Boolean> {
}

