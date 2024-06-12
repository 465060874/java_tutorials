package com.javatutorials.java.function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

// BiFunction接口在Java中有许多常见的使用场景，主要用于处理需要两个输入参数并生成一个输出结果的情况。
public class BiFunctionUsage {
    //BiFunction可以用于执行各种数学运算，例如加法、减法、乘法和除法。
    @Test
    public void test_maths() {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        int sum = add.apply(5, 3); // 输出: 8
        int product = multiply.apply(5, 3); // 输出: 15

        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
    }

    //BiFunction可以用于字符串的连接、比较等操作。
    @Test
    public void test_string_concatenation() {
        BiFunction<String, String, String> concatenate = (a, b) -> a + b;
        String result = concatenate.apply("Hello, ", "world!"); // 输出: "Hello, world!"
        System.out.println(result);
    }

    //在处理集合时，可以使用BiFunction来计算两个集合的对应元素。
    @Test
    public void test_collection_operations() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        List<Integer> result = list1.stream()
                .map(i -> add.apply(i, list2.get(list1.indexOf(i))))
                .collect(Collectors.toList());

        System.out.println(result); // 输出: [5, 7, 9]
    }

    //BiFunction可以用于处理复杂对象，例如合并两个对象的属性或计算两个对象的差异。
    @Test
    public void test_complex_objects() {
        BiFunction<Person, Person, String> combineNames = (p1, p2) -> p1.getName() + " & " + p2.getName();

        Person person1 = new Person("John");
        Person person2 = new Person("Jane");

        String combinedNames = combineNames.apply(person1, person2);
        System.out.println(combinedNames); // 输出: John & Jane
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    //BiFunction可以用于将两个输入参数转换为一个输出结果，例如将两个列表合并为一个列表，或将两个输入值映射为一个新的数据结构。

    @Test
    public void test_list_merge() {
        BiFunction<String, Integer, String> mapEntryToString = (key, value) -> key + "=" + value;

        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);

        map.forEach((key, value) -> {
            String result = mapEntryToString.apply(key, value);
            System.out.println(result);
        });

        // 输出:
        // apple=1
        // banana=2
    }

}
