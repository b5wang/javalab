package com.b5wang.javalab.java8recipes.concurrency.join;

class SubThread extends Thread{

    public void run(){
        System.out.println("SubThread start!");
        for(int i = 0; i < 1000; i++){
            // Wait
        }
        System.out.println("SubThread end!");
    }

    public static void main(String[] args){
        SubThread subThread = new SubThread();
        subThread.start();
        System.out.println("Main thread end!");
    }

}
