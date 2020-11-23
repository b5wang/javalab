package com.b5wang.javalab.java8recipes.concurrency.join;

public class JoinWithinMultiThreads {

    /**
     * A, B, C 3 threads, one calls join, which threads will wait for it?
     * */
    public static void main(String[] args) throws InterruptedException {
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i ++){
                    System.out.println("Thread - A");
                }
                System.out.println("Thread - A : finished");
            }
        });

        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i ++){
                    System.out.println("Thread - B");
                }
                System.out.println("Thread - B : finished");
            }
        });

        Thread tC = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i ++){
                    System.out.println("Thread - C");
                }
                System.out.println("Thread - C : finished");
            }
        });

        tA.start();
        System.out.println("Thread A started!");
        tB.start();
        System.out.println("Thread B started!");
        tA.join();
        tC.start();
        System.out.println("Thread C started!");
    }


}
