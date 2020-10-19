package com.b5wang.javalab.java8recipes.concurrency;

/**
 * public
 * class Thread implements Runnable
 * */
public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }
}
