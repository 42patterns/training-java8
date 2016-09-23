package com.example.foo.java8;

import com.example.foo.java8.funcset.FunctionalSet;
import org.junit.Test;

import static com.example.foo.java8.funcset.FunctionalSet.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class J10_FunctionalSetTest {

    final FunctionalSet one = singletonSet(1);
    final FunctionalSet two = singletonSet(2);
    final FunctionalSet three = singletonSet(3);

    @Test
    public void is_natural_number() {
        final FunctionalSet naturalNumbers = naturalNumbers();
        assertTrue(naturalNumbers.contains(1));
        assertFalse(naturalNumbers.contains(-1));
    }

    @Test
    public void singleton_should_contain_itself() {
        assertTrue(one.contains(1));
        assertTrue(two.contains(2));
        assertTrue(three.contains(3));
        assertFalse(three.contains(1));
    }

    @Test
    public void union_contains_all_elements() {
        final FunctionalSet u = one.union(two);
        assertTrue(u.contains(1));
        assertTrue(u.contains(2));
        assertFalse(u.contains(3));
    }

    @Test
    public void intersects_contains_only_one_element() {
        final FunctionalSet u = one.union(two);
        final FunctionalSet i = one.intersect(u);
        assertTrue(i.contains(1));
        assertFalse(i.contains(2));
    }

    @Test
    public void diff_contains_elements_from_one() {
        final FunctionalSet s = one.diff(two);
        assertTrue(s.contains(1));
        assertFalse(s.contains(2));
    }

    @Test
    public void filter_should_contains_only_elements_from_one() {
        final FunctionalSet s = one.diff(two);
        final FunctionalSet f = s.filter(x -> x == 1);
        assertTrue(f.contains(1));
        assertFalse(f.contains(2));
        assertFalse(f.contains(1000));
    }

    @Test
    public void not_all_elements_equal_one_for_union() {
        final FunctionalSet set = one.union(two);
        assertTrue(set.forall(i -> true));
        assertFalse(set.forall(i -> i == 1));
    }

    @Test
    public void exists_in_union() {
        final FunctionalSet set = one.union(two);
        assertTrue(set.exists(i -> i == 1));
        assertFalse(set.exists(i -> i == 3));
    }

    @Test
    public void map() {
        final FunctionalSet set = naturalNumbers();
        final FunctionalSet mappedSet = set.map(x ->  x*x);
        assertTrue(mappedSet.contains(1));
        assertFalse(mappedSet.contains(2));
        assertFalse(mappedSet.contains(3));
        assertTrue(mappedSet.contains(4));
        assertTrue(mappedSet.contains(9));
    }


}