package com.rosefinch.example.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class GroupBy {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(5);

        students.add(new Student("A","张三",10, Arrays.asList("李四","王五")));
        students.add(new Student("B","李四",9, Arrays.asList("李四","张三")));
        students.add(new Student("A","王五",11, Arrays.asList("李四","张三")));
        students.add(new Student("C","C罗",10, Arrays.asList("梅西","张三")));
        students.add(new Student("C","卡卡",10, Arrays.asList("梅西","李四")));


        final Map<String, List<Student>> gradeGroups =
                students.stream().collect(Collectors.groupingBy(Student::getGrade));
        System.out.println("gradeGroups=\n"+gradeGroups);

        final Map<String,Long>  gradeAges = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println("gradeAges =\n"+gradeAges);

        final Map<String,Double>  averagingGradeAges = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.averagingInt(Student::getAge)));
        System.out.println("averagingGradeAges =\n"+averagingGradeAges);

        final Map<String,Integer>  totalGradeAges = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.summingInt(Student::getAge)));
        System.out.println("totalGradeAges =\n"+totalGradeAges);

        //对Map按照分组年龄总值逆序排序
        Map<String, Integer> finalMap = new LinkedHashMap<>();
        totalGradeAges.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println("finalMap =\n"+finalMap);

        Map<String, Map<Integer, List<Student>>> doubleGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.groupingBy(Student::getAge)));

        System.out.println("doubleGroup =\n"+doubleGroup);

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student{

        private String grade;

        private String name;

        private Integer age;

        private List<String> friends;
    }
}
