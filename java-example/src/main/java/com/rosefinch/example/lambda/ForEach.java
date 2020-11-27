package com.rosefinch.example.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ForEach {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(e->{
            if("b".equals(e)){
                return;
            }
            System.out.println(e);
            System.out.println(1);
        });
    }
}
