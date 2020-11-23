package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Person {

    private String name;

    private int age;

    Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString(){
        return "{name: " + name +", age: " + age + "}";
    }


    // Test
    public static void main(String[] args){
        Person cp1 = new Person("Tom",25);
        Person cp2 = new Person("Tom",25);
        Person cp3 = new Person("Jerry",30);

        Set<Person> set1 = new HashSet<>();
        set1.add(cp1);
        set1.add(cp2);
        set1.add(cp3);
        System.out.println(Arrays.toString(set1.toArray()));// Sequence is based on hashCode mode array length

        // Exception in thread "main" java.lang.ClassCastException:
        // com.b5wang.javalab.java8recipes.collectionsframework.Person cannot be cast to java.lang.Comparable
//        Set<Person> set2 = new TreeSet<>();
//        set2.add(cp1);
//        set2.add(cp2);
//        set2.add(cp3);
//        System.out.println(Arrays.toString(set2.toArray()));

        Set<Person> set3 = new LinkedHashSet<>();
        set3.add(cp1);
        set3.add(cp2);
        set3.add(cp3);
        System.out.println(Arrays.toString(set3.toArray()));// Sequence follows adding order

        Set<Person> set4 = new TreeSet<>((p1,p2) -> p2.getAge() - p1.getAge());
        set4.add(cp1);
        set4.add(cp2);
        set4.add(cp3);
        System.out.println(Arrays.toString(set4.toArray()));
    }

}
