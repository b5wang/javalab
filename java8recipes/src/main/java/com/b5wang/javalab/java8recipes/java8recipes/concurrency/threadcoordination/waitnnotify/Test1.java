package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.waitnnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 1 producer and 1 consumer
 * */
public class Test1 {

    public static void main(String[] args) {
        List<Integer> cache = new ArrayList<>();
        new Thread(new Producer(cache), "Producer_1").start();
        new Thread(new Customer(cache), "Consumer_1").start();
    }

}
