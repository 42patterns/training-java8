package com.example.foo.java8.funcset;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * We represent a set by its characteristic function, i.e.
 * its `contains` predicate.
 */
@FunctionalInterface
public interface FunctionalSet extends Function<Integer, Boolean> {
    final static Integer BOUND = 1000;

    /**
     * Returns the set of the one given element.
     */
    static FunctionalSet singletonSet(Integer i) {
        return x -> x == i;
    }

    /**
     * Return infinite set of natural numbers
     */
    static FunctionalSet naturalNumbers() {
        return x -> x > 0;
    }

    /**
     * Indicates whether a set contains a given element.
     */
    default Boolean contains(Integer i) {
        return apply(i);
    }

    /**
     * Returns the union of the two given sets,
     * the sets of all elements that are in either `this` or `other`.
     */
    default FunctionalSet union(FunctionalSet other) {
        return x -> this.contains(x) || other.contains(x);
    }

    /**
     * Returns the intersection of the two given sets,
     * the set of all elements that are both in `this` or `other`.
     */
    default FunctionalSet intersect(FunctionalSet other) {
        return x -> this.contains(x) && other.contains(x);
    }

    /**
     * Returns the difference of the two given sets,
     * the set of all elements of `this` that are not in `other`.
     */
    default FunctionalSet diff(FunctionalSet other) {
        return x -> this.contains(x) && !other.contains(x);
    }

    /**
     * Returns the subset of `this` for which `p` holds.
     */
    default FunctionalSet filter(Predicate<Integer> p) {
        return x -> this.contains(x) && p.test(x);
    }

    /**
     * Returns whether all bounded integers within `this` satisfy `p`.
     */
    default Boolean forall(Predicate<Integer> p) {
        return IntStream.range(-1*BOUND, BOUND)
                .filter(this::contains)
                .allMatch(p::test);
    }

    /**
     * Returns whether there exists a bounded integer within `this`
     * that satisfies `p`.
     */
    default Boolean exists(Predicate<Integer> p) {
        return IntStream.range(-1*BOUND, BOUND)
                .filter(this::contains)
                .anyMatch(p::test);
    }

    /**
     * Returns a set transformed by applying `f` to each element of `this`
     */
    default FunctionalSet map(Function<Integer, Integer> f) {
        return x -> exists(i -> x == f.apply(i));
    }

    default void print() {
        IntStream.range(BOUND * -1, BOUND)
                .filter( i -> this.contains(i))
                .forEach(i -> System.out.println("{" + i + "}"));
    }
}
