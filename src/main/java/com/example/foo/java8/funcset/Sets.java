package com.example.foo.java8.funcset;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public interface Sets {
    final static Integer BOUND = 1000;

    /**
     * Returns the set of the one given element.
     */
    Function<Integer, Set> singletonSet = i -> x -> x == i;

    /**
     * Indicates whether a set contains a given element.
     */
    BiFunction<Set, Integer, Boolean> contains = (Set s, Integer i) -> s.apply(i);

    /**
     * Returns the union of the two given sets,
     * the sets of all elements that are in either `s` or `t`.
     */
    BiFunction<Set, Set, Set> union = (Set s, Set t) ->
        x -> contains.apply(s,x) || contains.apply(t,x);

    /**
     * Returns the intersection of the two given sets,
     * the set of all elements that are both in `s` or `t`.
     */
    BiFunction<Set, Set, Set> intersect = (s, t) -> {
        throw new UnsupportedOperationException("Not implemented");
    };


    /**
     * Returns the difference of the two given sets,
     * the set of all elements of `s` that are not in `t`.
     */
    BiFunction<Set, Set, Set> diff = (s, t) -> {
        throw new UnsupportedOperationException("Not implemented");
    };

    /**
     * Returns the subset of `s` for which `p` holds.
     */
    BiFunction<Set, Predicate<Integer>, Set> filter = (s, p) -> {
        throw new UnsupportedOperationException("Not implemented");
    };

    /**
     * Returns whether all bounded integers within `s` satisfy `p`.
     */
    BiFunction<Set, Predicate<Integer>, Boolean> forall = (s, p) -> {
        throw new UnsupportedOperationException("Not implemented");
    };

    /**
     * Returns whether there exists a bounded integer within `s`
     * that satisfies `p`.
     */
    BiFunction<Set, Predicate<Integer>, Boolean> exists = (s, p) -> {
        throw new UnsupportedOperationException("Not implemented");
    };

    /**
     * Returns a set transformed by applying `f` to each element of `s`.
     */
    BiFunction<Set, Function<Integer, Integer>, Set> map = (s, f) -> {
        throw new UnsupportedOperationException("Not implemented");
    };

    default void printSet(Set s) {
        IntStream.range(BOUND * -1, BOUND)
                .filter( i -> contains.apply(s, i))
                .forEach(i -> System.out.println("{" + i + "}"));
    }

}
