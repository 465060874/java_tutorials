package com.javatutorials.java.stream;

import com.javatutorials.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSorting {
    @Test
    public void test_sorting_by_lastname_firstname_age() {
        List<Person> people = Arrays.asList(
                new Person("John", "Doe", 30),
                new Person("Jane", "Smith", 25),
                new Person("John", "Smith", 35),
                new Person("Jane", "Doe", 22)
        );

        List<Person> sortedPeople = people.stream()
                .sorted(Comparator.comparing(Person::getLastName)
                        .thenComparing(Person::getFirstName)
                        .thenComparingInt(Person::getAge))
                .collect(Collectors.toList());

        sortedPeople.forEach(System.out::println);
    }
}
