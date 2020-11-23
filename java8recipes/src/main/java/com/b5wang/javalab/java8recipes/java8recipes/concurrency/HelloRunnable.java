package com.b5wang.javalab.java8recipes.concurrency;

/**
 * Runnable, FunctionalInterface from java 1.0
 * */
public class HelloRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start();
    }
}
