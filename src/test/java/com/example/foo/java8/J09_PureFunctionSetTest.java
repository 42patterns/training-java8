package com.example.foo.java8;

import com.example.foo.java8.funcset.Set;
import org.junit.Test;

import static com.example.foo.java8.funcset.Sets.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class J09_PureFunctionSetTest {

    final Set one = singletonSet.apply(1);
    final Set two = singletonSet.apply(2);
    final Set three = singletonSet.apply(3);


    @Test
    public void singleton_should_contain_itself() {
        assertTrue(contains.apply(one, 1));
        assertTrue(contains.apply(two, 2));
        assertTrue(contains.apply(three, 3));
    }

    @Test
    public void union_contains_all_elements() {
        Set u = union.apply(one, two);
        assertTrue(contains.apply(u, 1));
        assertTrue(contains.apply(u, 2));
        assertFalse(contains.apply(u, 3));
    }

    @Test
    public void intersects_contains_only_one_element() {
        Set u = union.apply(one, two);
        Set s = intersect.apply(one, u);
        assertTrue(contains.apply(s, 1));
        assertFalse(contains.apply(s, 2));
    }

    @Test
    public void diff_contains_elements_from_one() {
        Set s = diff.apply(one, two);
        assertTrue(contains.apply(s, 1));
        assertFalse(contains.apply(s, 2));
    }


    @Test
    public void filter_should_contains_only_elements_from_one() {
        Set set = union.apply(one, two);
        Set f = filter.apply(set, x -> x == 1);
        assertTrue(contains.apply(f, 1));
        assertFalse(contains.apply(f, 2));
        assertFalse(contains.apply(f, 1000));
    }

    @Test
    public void not_all_elements_equal_one_for_union() {
        Set set = union.apply(one, two);
        assertTrue(forall.apply(set, i -> true));
        assertFalse(forall.apply(set, i -> i == 1));
    }


    @Test
    public void exists_in_union() {
        Set set = union.apply(one, two);
        assertTrue(exists.apply(set, i -> i == 1));
        assertFalse(exists.apply(set, i -> i == 3));
    }

    @Test
    public void map() {
        Set set = map.apply(union.apply(union.apply(one, two), three), x ->  x*x);
        assertTrue(contains.apply(set, 1));
        assertTrue(contains.apply(set, 4));
        assertTrue(contains.apply(set, 9));
    }
}
