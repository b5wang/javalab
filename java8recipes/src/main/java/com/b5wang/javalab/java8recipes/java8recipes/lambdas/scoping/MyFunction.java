package com.b5wang.javalab.java8recipes.lambdas.scoping;

import java.util.function.Function;

@FunctionalInterface
public interface MyFunction<R,T> {

    // static final field
    String MSG = "Hello, MyFunction!";

    // abstract method
    T apply(R r);

    // default method
    default void printSomething(){
        System.out.println("printSomething() - " + MSG);
    }

    // static nested class
    static class DefaultMyFunction implements MyFunction<String,String>{

        @Override
        public String apply(String s) {
            return "DefaultMyFunction: " + s;
        }
    }

}
