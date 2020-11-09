package com.rosefinch.example.lambda;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class AnyMatch {

    public static void main(String[] args) {


        List<String> list = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = list.stream().anyMatch(str -> str.equals("a"));
        boolean bb = list.stream().allMatch(str -> str.equals("a"));
        boolean cc = list.stream().noneMatch(str -> str.equals("a"));
        long count = list.stream().filter(str -> str.equals("a")).count();


        System.out.println(aa);
        System.out.println(bb);
        System.out.println(cc);
        System.out.println(count);


        List<String> aList = new ArrayList<>(0);
        System.out.println("a list is empty?="+ CollectionUtils.isEmpty(aList));

        aList.add("a");
        aList.add("b");
        aList.add("c");

        System.out.println("a list is empty?="+ CollectionUtils.isEmpty(aList));
        System.out.println("a list =="+aList);





    }
}
