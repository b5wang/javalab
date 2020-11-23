package com.b5wang.javalab.java8recipes.lambdas;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaSyntax {

    /**
     * You can understand lambda as anonymous method, close to a freestanding method.
     * */
    public static void main(String[] args){
        // 0 parameter, no return
        Runnable task1 = () -> System.out.println("Hello world!");
        // 0 parameter, no return
        Runnable task2 = () -> {
            System.out.println("Hello world!");
            return;// return nothing
        };

        // 0 parameter, 1 return
        Supplier<String> supplier1 = () -> "Hello, supplier!";
        Supplier<String> supplier2 = () -> {
            return "Hello, supplier!";
        };

        // 1 parameter, no return
        Consumer<String> consumer1 = s -> System.out.println(s);
        Consumer<String> consumer2 = (s) -> System.out.println(s);
        Consumer<String> consumer3 = (String s) -> System.out.println(s);
        // 1 parameter, with return
        Function<String,String> fun1 = (s) -> "Hello, " + s + "!";
        Function<String,String> fun2 = (String s) -> "Hello, " + s + "!";
        Function<String,String> fun3 = (String s) -> {
            return "Hello, " + s + "!";
        };

        // 2 or more parameter
        BiFunction<String,String,String> biFun1 = (s1,s2) -> s1 + s2;
        BiFunction<String,String,String> biFun2 = (String s1,String s2) -> s1 + s2;
        BiFunction<String,String,String> biFun3 = (s1,s2) -> {
                return s1 + s2;
        };
    }

}
