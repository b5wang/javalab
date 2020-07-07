package com.b5wang.javalab.concurrency;

public class HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello Runnable!");
    }
}
