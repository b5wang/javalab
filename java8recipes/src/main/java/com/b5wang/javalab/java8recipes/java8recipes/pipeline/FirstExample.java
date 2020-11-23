package com.b5wang.javalab.java8recipes.pipeline;

import java.util.stream.IntStream;

public class FirstExample {

    public static void main(String[] args){
        IntStream.iterate(1, i->i*2).limit(10).forEachOrdered(System.out::println);
    }

}
