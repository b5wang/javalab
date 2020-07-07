package com.b5wang.javalab.concurrency;

/**
 * Define multiple thread tasks:
 * 1. Implement Runnable (Functional interface), or lambda expression to the thread constructor
 * 2. Extend Thread and overrides the run() method
 * */
public class HelloThread extends Thread{

    @Override
    public void run(){
        System.out.println("Hello Thread!");
    }

    public static void main(String[] args){
        new Thread(new HelloRunnable()).start();
        new Thread(()->{
            System.out.println("Hello World!");
        }).start();
        new HelloThread().start();
    }
}
