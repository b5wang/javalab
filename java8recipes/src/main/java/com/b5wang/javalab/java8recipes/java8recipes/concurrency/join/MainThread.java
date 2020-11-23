package com.b5wang.javalab.java8recipes.concurrency.join;

class MainThread extends Thread{

    public void run(){
        System.out.println("MainThread start!");
        SubThread subThread = new SubThread();
        subThread.start();
        System.out.println("MainThread end!");
    }

    public static void main(String[] args){
        MainThread mainThread = new MainThread();
        mainThread.start();
    }

}
