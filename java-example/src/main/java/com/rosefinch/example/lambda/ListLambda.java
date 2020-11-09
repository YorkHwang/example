package com.rosefinch.example.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ListLambda {




    public static void main(String[] args) {

        List<String> languages = Arrays.asList("GO", "Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->((String)str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->((String)str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->((String)str).length() > 4);


        //languages.add("11");
        List<String> list = new ArrayList<>(0);
        list.add("GO");
        list.add("Java");
        list.stream().forEach(l->System.out.println(l));
        System.out.println(list);
    }

    public static void filter(List<String> names, Predicate condition) {
        for(String  name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }




}
