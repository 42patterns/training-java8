package com.example.foo.java8;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class J04_StreamsBasicsTest {

    @Test
    public void should_convert_elements_to_upper_case() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        assertThat(ToUpperCase.transform(collection), is(equalTo(expected)));
    }

    @Test
    public void should_filter_collection() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        assertThat(FilterCollection.transform(collection), is(equalTo(expected)));
    }

    @Test
    public void should_flatten_collection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");
        assertThat(FlatCollection.transform(collection), is(equalTo(expected)));
    }

    @Test
    public void should_return_oldest_person() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        assertThat(OldestPerson.getOldestPerson(collection), is(equalTo(eva)));
    }

    @Test
    public void should_sum_all_elements() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertThat(Sum.calculate(numbers), equalTo(1 + 2 + 3 + 4 + 5));
    }

    @Test
    public void should_return_only_kids_names() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        assertThat(Kids.getKidNames(collection), contains("Sara", "Anna"));
        assertThat(Kids.getKidNames(collection), not(contains("Viktor", "Eva")));
    }

    @Test
    public void should_partition_by_nationality() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = Grouping.groupByNationality(collection);
        assertThat(result.get("Norwegian"), contains(sara, eva));
        assertThat(result.get("Serbian"), contains(viktor));
    }

    @Test
    public void should_return_people_name_separated_by_comma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        assertThat(Joining.namesToString(collection), equalTo("Names: Sara, Viktor, Eva."));
    }

    public static class ToUpperCase {
        public static List<String> transform(List<String> collection) {
            return collection.stream() // Convert collection to Stream
                    .map(String::toUpperCase) // Convert each element to upper case
                    .collect(toList()); // Collect results to a new list
        }
    }

    public static class FilterCollection {

        public static List<String> transform(List<String> collection) {
            return collection.stream() // Convert collection to Stream
                    .filter(value -> value.length() < 4) // Filter elements with length smaller than 4 characters
                    .collect(toList()); // Collect results to a new list
        }
    }

    public static class FlatCollection {

        public static List<String> transform(List<List<String>> collection) {
            return collection.stream() // Convert collection to Stream
                    .flatMap(value -> value.stream()) // Replace list with stream
                    .collect(toList()); // Collect results to a new list
        }
    }

    public static class Person {

        private final String name;
        private final int age;
        private String nationality;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, String nationality) {
            this.name = name;
            this.age = age;
            this.nationality = nationality;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getNationality() {
            return nationality;
        }
    }

    public static class OldestPerson {

        public static Person getOldestPerson(List<Person> people) {
            return people.stream() // Convert collection to Stream
                    .max(Comparator.comparing(Person::getAge)) // Compares people ages
                    .get(); // Gets stream result
        }

    }

    public static class Sum {
        public static int calculate(List<Integer> numbers) {
            return numbers.stream() // Convert collection to Stream
                    .reduce(0, (total, number) -> total + number); // Sum elements with 0 as starting value
        }
    }

    public static class Kids {
        public static Set<String> getKidNames(List<Person> people) {
            return people.stream()
                    .filter(person -> person.getAge() < 18) // Filter kids (under age of 18)
                    .map(Person::getName) // Map Person elements to names
                    .collect(toSet()); // Collect values to a Set
        }
    }

    public static class Grouping {
        public static Map<String, List<Person>> groupByNationality(List<Person> people) {
            return people.stream() // Convert collection to Stream
                    .collect(groupingBy(Person::getNationality)); // Group people by nationality
        }
    }

    public static class Joining {


        public static String namesToString(List<Person> people) {
            return people.stream() // Convert collection to Stream
                    .map(Person::getName) // Map Person to name
                    .collect(joining(", ", "Names: ", ".")); // Join names
        }

    }
}
