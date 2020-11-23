package com.b5wang.javalab.java8recipes.lambdas.usage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class InstanceMethodReferences {

    private static class Person {

        private String name;

        private int age;

        public Person(String name,int age){
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

        public String toString(){
            return "(Person: " +name + "," + age + ")";
        }
    }

    private static class Worker extends Person{
        public Worker(String name, int age) {
            super(name, age);
        }
    }

    private static class Teacher extends Person{
        public Teacher(String name, int age) {
            super(name, age);
        }
    }


    /**
     * Bound method reference
     * Unbound method reference
     * */
    public static void main(String[] args){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        // bound method reference, the receiver is fixed, always System.out
        intList.stream().forEach(System.out::println);

        // public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
        Comparator com = Comparator.comparing(Person::getAge);
        List<Worker> personList = Arrays.asList(
                new Worker("Tom",21),
                new Worker("John",19),
                new Worker("Jerry",82),
                new Worker("Stephen",42));

        Worker[] personArray = personList.toArray(new Worker[0]);
        System.out.println(Arrays.toString(personArray));
        Arrays.sort(personArray,com);
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(personArray));
    }

}
