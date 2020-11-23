package com.b5wang.javalab.java8recipes.concurrency.inturrupt;

public class InterruptNoCatch implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            System.out.println(i);
        }
        System.out.println("Thread finish!");
    }

    public static void main(String[] args){
        Thread thread = new Thread(new InterruptNoCatch());
        thread.start();
        thread.interrupt();//If no catching interrupted status inside thread, thread still run.
    }
}
