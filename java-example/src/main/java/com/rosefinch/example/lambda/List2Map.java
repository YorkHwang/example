package com.rosefinch.example.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class List2Map {

    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(
                new Apple("1","a"),
                new Apple("2","c"),
                new Apple("3","d"),
                new Apple("1","e"));
        Map<String,Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));



        System.out.println(appleMap);
    }

    @Data
    @AllArgsConstructor
    static class Apple{
        private String id;
        private String color;
    }
}
