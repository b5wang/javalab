package com.b5wang.javalab.concurrency.streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class StatefulOperations {

    public static void main(String[] args){
        // Get parallel stream from stream
        Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
        Stream<Integer> parallelStream = stream.parallel();

        // Get parallel stream from collection
        Stream<Integer> parallelStream2 = Arrays.asList(1,2,3,4,5,6).parallelStream();
    }

}
