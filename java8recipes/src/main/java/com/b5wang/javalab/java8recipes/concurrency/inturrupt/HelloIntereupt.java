package com.b5wang.javalab.java8recipes.concurrency.inturrupt;

public class HelloIntereupt implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 50000; i++){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Thread exit! (Thread is interrupted)");
                return;
            }
            System.out.println("i = " + i);
        }
        System.out.println("Thread exit! (Run till the end)");
    }

    public static void main(String[] args){
        Thread thread = new Thread(new HelloIntereupt());
        thread.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Main thread stopped!");
        }
        thread.interrupt();// Stop the thread
    }
}
