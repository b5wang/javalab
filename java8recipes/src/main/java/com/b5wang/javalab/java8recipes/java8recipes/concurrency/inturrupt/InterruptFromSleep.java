package com.b5wang.javalab.java8recipes.concurrency.inturrupt;

public class InterruptFromSleep implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("Thread start");
            Thread.sleep(200);
            System.out.println("Thread end!");
        } catch (InterruptedException e) {
            System.out.println("Exit from thread sleep!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptFromSleep());
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
