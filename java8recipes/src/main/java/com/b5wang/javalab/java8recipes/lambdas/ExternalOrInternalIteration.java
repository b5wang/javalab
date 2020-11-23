package com.b5wang.javalab.java8recipes.lambdas;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ExternalOrInternalIteration {

    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        // External iteration 1 before java5 introduce for-each loop statement
        // Before java 1.5, after java 1.2
        // Style (1)
        Iterator<Integer> listIterator = list.iterator();
        while(listIterator.hasNext()){
            System.out.println("External iteration 1: " + listIterator.next());
        }

        // Style (2)
        for(Iterator<Integer> iterator = list.iterator(); iterator.hasNext();){
            System.out.println("External iteration 2: " + iterator.next());
        }

        // External iteration 3
        // After java 1.5
        for(Integer i : list){
            System.out.println("For-each: " + i);
        }

        // Internal iteration
        // After java 8
        list.forEach(el->System.out.println("Internal iteration - collection.forEach: " + el));
        list.stream().forEach(el->System.out.println("Internal iteration - stream.forEach: " + el));
        list.parallelStream().forEach(el->System.out.println("Internal iteration - parallelStream.forEach: " + el));

    }

}
