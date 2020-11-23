package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.waitnnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 1 producer, 2 consumers,
 * Finally go to deadlock
 * */
public class Test2 {
    public static void main(String[] args) {

        List<Integer> cache = new ArrayList<>();

        new Thread(new Producer(cache), "Producer-1").start();
        new Thread(new Customer(cache), "Consumer-1").start();
        new Thread(new Customer(cache), "Consumer-2").start();
    }
}
